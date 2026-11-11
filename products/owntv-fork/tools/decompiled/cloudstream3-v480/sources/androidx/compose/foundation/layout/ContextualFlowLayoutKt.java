package androidx.compose.foundation.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;

/* JADX INFO: compiled from: ContextualFlowLayout.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0084\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r21\u0010\u000e\u001a-\u0012\u0004\u0012\u00020\u0010\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001a\u0084\u0001\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u001921\u0010\u000e\u001a-\u0012\u0004\u0012\u00020\u001a\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u001b\u001a¥\u0001\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010#\u001a\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00010%¢\u0006\u0002\b\u00140$2;\u0010&\u001a7\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110'¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0014H\u0001¢\u0006\u0002\u0010)\u001a¥\u0001\u0010*\u001a\u0014\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u000f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010#\u001a\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00010%¢\u0006\u0002\b\u00140$2;\u0010&\u001a7\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110'¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0014H\u0001¢\u0006\u0002\u0010+¨\u0006,"}, d2 = {"ContextualFlowColumn", "", "itemCount", "", "modifier", "Landroidx/compose/ui/Modifier;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "maxItemsInEachColumn", "maxLines", "overflow", "Landroidx/compose/foundation/layout/ContextualFlowColumnOverflow;", "content", "Lkotlin/Function2;", "Landroidx/compose/foundation/layout/ContextualFlowColumnScope;", "Lkotlin/ParameterName;", "name", "index", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IILandroidx/compose/foundation/layout/ContextualFlowColumnOverflow;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "ContextualFlowRow", "maxItemsInEachRow", "Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "Landroidx/compose/foundation/layout/ContextualFlowRowScope;", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;IILandroidx/compose/foundation/layout/ContextualFlowRowOverflow;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "contextualColumnMeasureHelper", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "maxItemsInMainAxis", "overflowState", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "overflowComposables", "", "Lkotlin/Function0;", "getComposable", "Landroidx/compose/foundation/layout/FlowLineInfo;", "info", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;ILjava/util/List;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "contextualRowMeasurementHelper", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;ILjava/util/List;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContextualFlowLayoutKt {
    /* JADX WARN: Code duplicated, block: B:102:0x0132  */
    /* JADX WARN: Code duplicated, block: B:103:0x0134  */
    /* JADX WARN: Code duplicated, block: B:105:0x0137  */
    /* JADX WARN: Code duplicated, block: B:106:0x0139  */
    /* JADX WARN: Code duplicated, block: B:108:0x013d  */
    /* JADX WARN: Code duplicated, block: B:109:0x0144  */
    /* JADX WARN: Code duplicated, block: B:112:0x014c  */
    /* JADX WARN: Code duplicated, block: B:115:0x0165  */
    /* JADX WARN: Code duplicated, block: B:116:0x0167  */
    /* JADX WARN: Code duplicated, block: B:119:0x016e  */
    /* JADX WARN: Code duplicated, block: B:121:0x0176  */
    /* JADX WARN: Code duplicated, block: B:124:0x018d  */
    /* JADX WARN: Code duplicated, block: B:125:0x018f  */
    /* JADX WARN: Code duplicated, block: B:128:0x0196  */
    /* JADX WARN: Code duplicated, block: B:130:0x019e  */
    /* JADX WARN: Code duplicated, block: B:133:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:137:0x0202  */
    /* JADX WARN: Code duplicated, block: B:139:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0052  */
    /* JADX WARN: Code duplicated, block: B:27:0x0055  */
    /* JADX WARN: Code duplicated, block: B:29:0x0059  */
    /* JADX WARN: Code duplicated, block: B:31:0x0061  */
    /* JADX WARN: Code duplicated, block: B:32:0x0064  */
    /* JADX WARN: Code duplicated, block: B:37:0x006e  */
    /* JADX WARN: Code duplicated, block: B:38:0x0071  */
    /* JADX WARN: Code duplicated, block: B:40:0x0075  */
    /* JADX WARN: Code duplicated, block: B:42:0x007d  */
    /* JADX WARN: Code duplicated, block: B:43:0x0080  */
    /* JADX WARN: Code duplicated, block: B:48:0x008a  */
    /* JADX WARN: Code duplicated, block: B:49:0x008d  */
    /* JADX WARN: Code duplicated, block: B:51:0x0091  */
    /* JADX WARN: Code duplicated, block: B:53:0x0099  */
    /* JADX WARN: Code duplicated, block: B:54:0x009c  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:60:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:65:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:70:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00da  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:82:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:89:0x0100  */
    /* JADX WARN: Code duplicated, block: B:94:0x0114 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x0116  */
    /* JADX WARN: Code duplicated, block: B:97:0x011d  */
    /* JADX WARN: Code duplicated, block: B:99:0x0126  */
    public static final void ContextualFlowRow(final int i, Modifier modifier, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, int i2, int i3, ContextualFlowRowOverflow contextualFlowRowOverflow, final Function4<? super ContextualFlowRowScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i4, final int i5) {
        int i6;
        Modifier modifier2;
        int i7;
        Arrangement.Horizontal start;
        int i8;
        int i9;
        Arrangement.Vertical top;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        ContextualFlowRowOverflow clip;
        int i21;
        boolean z;
        Object objRememberedValue;
        FlowLayoutOverflowState flowLayoutOverflowState;
        boolean z2;
        Object objRememberedValue2;
        Object obj;
        final ContextualFlowRowOverflow contextualFlowRowOverflow2;
        final Arrangement.Horizontal horizontal2;
        final int i22;
        Composer composer2;
        final Arrangement.Vertical vertical2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-341770689);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ContextualFlowRow)P(2,5,1,7,3,4,6)74@3434L65,77@3545L193,90@3961L267,82@3763L465,99@4233L90:ContextualFlowLayout.kt#2w3rfo");
        if ((i5 & 1) != 0) {
            i6 = i4 | 6;
        } else if ((i4 & 6) == 0) {
            i6 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i4;
        } else {
            i6 = i4;
        }
        int i23 = i5 & 2;
        if (i23 == 0) {
            if ((i4 & 48) == 0) {
                modifier2 = modifier;
                i6 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i7 = i5 & 4;
            if (i7 != 0) {
                if ((i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    start = horizontal;
                    if (composerStartRestartGroup.changed(start)) {
                        i8 = 256;
                    } else {
                        i8 = 128;
                    }
                    i6 |= i8;
                }
                i9 = i5 & 8;
                if (i9 != 0) {
                    if ((i4 & 3072) == 0) {
                        top = vertical;
                        if (composerStartRestartGroup.changed(top)) {
                            i10 = 2048;
                        } else {
                            i10 = 1024;
                        }
                        i6 |= i10;
                    }
                    i11 = i5 & 16;
                    if (i11 != 0) {
                        if ((i4 & 24576) == 0) {
                            i12 = i2;
                            if (composerStartRestartGroup.changed(i12)) {
                                i13 = 16384;
                            } else {
                                i13 = 8192;
                            }
                            i6 |= i13;
                        }
                        i14 = i5 & 32;
                        if (i14 != 0) {
                            i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(i3)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i6 |= i15;
                        }
                        i16 = i5 & 64;
                        if (i16 != 0) {
                            i6 |= 1572864;
                        } else if ((i4 & 1572864) == 0) {
                            if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i6 |= i17;
                        }
                        if ((i5 & 128) != 0) {
                            i6 |= 12582912;
                        } else if ((i4 & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i18 = 8388608;
                            } else {
                                i18 = 4194304;
                            }
                            i6 |= i18;
                        }
                        if ((4793491 & i6) == 4793490 || !composerStartRestartGroup.getSkipping()) {
                            if (i23 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            }
                            if (i9 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            }
                            if (i11 != 0) {
                                i19 = Integer.MAX_VALUE;
                            } else {
                                i19 = i12;
                            }
                            if (i14 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i3;
                            }
                            if (i16 != 0) {
                                clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                            } else {
                                clip = contextualFlowRowOverflow;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                            i21 = 3670016 & i6;
                            if (i21 == 1048576) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                            if (i21 == 1048576) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                obj = objRememberedValue2;
                                ArrayList arrayList = new ArrayList();
                                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList);
                                composerStartRestartGroup.updateRememberedValue(arrayList);
                                obj = arrayList;
                            }
                            obj = objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i24 = i6 >> 6;
                            Arrangement.Horizontal horizontal3 = start;
                            Composer composer3 = composerStartRestartGroup;
                            SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal3, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(4);
                                }

                                @Override // kotlin.jvm.functions.Function4
                                public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer4, Integer num2) {
                                    invoke(num.intValue(), flowLineInfo, composer4, num2.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(int i25, FlowLineInfo flowLineInfo, Composer composer4, int i26) {
                                    ComposerKt.sourceInformation(composer4, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-8464804, i26, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                                    }
                                    function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i25), composer4, Integer.valueOf((i26 << 3) & 112));
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i24 & 7168) | (i24 & 14) | 12582912 | (i24 & 112) | (i24 & 896) | ((i6 << 15) & 458752)), composer3, (i6 >> 3) & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            contextualFlowRowOverflow2 = clip;
                            horizontal2 = horizontal3;
                            i22 = i20;
                            composer2 = composer3;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            i22 = i3;
                            composer2 = composerStartRestartGroup;
                            horizontal2 = start;
                            i19 = i12;
                            contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                        }
                        vertical2 = top;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            final Modifier modifier3 = modifier2;
                            final int i25 = i19;
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
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

                                public final void invoke(Composer composer4, int i26) {
                                    ContextualFlowLayoutKt.ContextualFlowRow(i, modifier3, horizontal2, vertical2, i25, i22, contextualFlowRowOverflow2, function4, composer4, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                                }
                            });
                        }
                    }
                    i6 |= 24576;
                    i12 = i2;
                    i14 = i5 & 32;
                    if (i14 != 0) {
                        i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i6 |= i15;
                    }
                    i16 = i5 & 64;
                    if (i16 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i6 |= i17;
                    }
                    if ((i5 & 128) != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((4793491 & i6) == 4793490) {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i9 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList2 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList2);
                            composerStartRestartGroup.updateRememberedValue(arrayList2);
                            obj = arrayList2;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList3 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList3);
                            composerStartRestartGroup.updateRememberedValue(arrayList3);
                            obj = arrayList3;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i26 = i6 >> 6;
                        Arrangement.Horizontal horizontal4 = start;
                        Composer composer4 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal4, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer5, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer5, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i27, FlowLineInfo flowLineInfo, Composer composer5, int i28) {
                                ComposerKt.sourceInformation(composer5, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-8464804, i28, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                                }
                                function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i27), composer5, Integer.valueOf((i28 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i26 & 7168) | (i26 & 14) | 12582912 | (i26 & 112) | (i26 & 896) | ((i6 << 15) & 458752)), composer4, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal2 = horizontal4;
                        i22 = i20;
                        composer2 = composer4;
                    } else {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i9 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList4 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList4);
                            composerStartRestartGroup.updateRememberedValue(arrayList4);
                            obj = arrayList4;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList5 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList5);
                            composerStartRestartGroup.updateRememberedValue(arrayList5);
                            obj = arrayList5;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i27 = i6 >> 6;
                        Arrangement.Horizontal horizontal5 = start;
                        Composer composer5 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal5, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer6, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer6, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i28, FlowLineInfo flowLineInfo, Composer composer6, int i29) {
                                ComposerKt.sourceInformation(composer6, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-8464804, i29, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                                }
                                function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i28), composer6, Integer.valueOf((i29 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i27 & 7168) | (i27 & 14) | 12582912 | (i27 & 112) | (i27 & 896) | ((i6 << 15) & 458752)), composer5, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal2 = horizontal5;
                        i22 = i20;
                        composer2 = composer5;
                    }
                    vertical2 = top;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier4 = modifier2;
                        final int i28 = i19;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer6, Integer num) {
                                invoke(composer6, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer6, int i29) {
                                ContextualFlowLayoutKt.ContextualFlowRow(i, modifier4, horizontal2, vertical2, i28, i22, contextualFlowRowOverflow2, function4, composer6, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                            }
                        });
                    }
                }
                i6 |= 3072;
                top = vertical;
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i6 |= i13;
                    }
                    i14 = i5 & 32;
                    if (i14 != 0) {
                        i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i6 |= i15;
                    }
                    i16 = i5 & 64;
                    if (i16 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i6 |= i17;
                    }
                    if ((i5 & 128) != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((4793491 & i6) == 4793490) {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i9 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList6 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList6);
                            composerStartRestartGroup.updateRememberedValue(arrayList6);
                            obj = arrayList6;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList7 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList7);
                            composerStartRestartGroup.updateRememberedValue(arrayList7);
                            obj = arrayList7;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i29 = i6 >> 6;
                        Arrangement.Horizontal horizontal6 = start;
                        Composer composer6 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal6, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer7, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer7, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i210, FlowLineInfo flowLineInfo, Composer composer7, int i211) {
                                ComposerKt.sourceInformation(composer7, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-8464804, i211, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                                }
                                function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i210), composer7, Integer.valueOf((i211 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i29 & 7168) | (i29 & 14) | 12582912 | (i29 & 112) | (i29 & 896) | ((i6 << 15) & 458752)), composer6, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal2 = horizontal6;
                        i22 = i20;
                        composer2 = composer6;
                    } else {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i9 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList8 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList8);
                            composerStartRestartGroup.updateRememberedValue(arrayList8);
                            obj = arrayList8;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList9 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList9);
                            composerStartRestartGroup.updateRememberedValue(arrayList9);
                            obj = arrayList9;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i210 = i6 >> 6;
                        Arrangement.Horizontal horizontal7 = start;
                        Composer composer7 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal7, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer8, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer8, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i211, FlowLineInfo flowLineInfo, Composer composer8, int i212) {
                                ComposerKt.sourceInformation(composer8, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-8464804, i212, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                                }
                                function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i211), composer8, Integer.valueOf((i212 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i210 & 7168) | (i210 & 14) | 12582912 | (i210 & 112) | (i210 & 896) | ((i6 << 15) & 458752)), composer7, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal2 = horizontal7;
                        i22 = i20;
                        composer2 = composer7;
                    }
                    vertical2 = top;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier5 = modifier2;
                        final int i211 = i19;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer8, Integer num) {
                                invoke(composer8, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer8, int i212) {
                                ContextualFlowLayoutKt.ContextualFlowRow(i, modifier5, horizontal2, vertical2, i211, i22, contextualFlowRowOverflow2, function4, composer8, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                            }
                        });
                    }
                }
                i6 |= 24576;
                i12 = i2;
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList10 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList10);
                        composerStartRestartGroup.updateRememberedValue(arrayList10);
                        obj = arrayList10;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11);
                        composerStartRestartGroup.updateRememberedValue(arrayList11);
                        obj = arrayList11;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i212 = i6 >> 6;
                    Arrangement.Horizontal horizontal8 = start;
                    Composer composer8 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal8, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer9, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer9, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i213, FlowLineInfo flowLineInfo, Composer composer9, int i214) {
                            ComposerKt.sourceInformation(composer9, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i214, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i213), composer9, Integer.valueOf((i214 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i212 & 7168) | (i212 & 14) | 12582912 | (i212 & 112) | (i212 & 896) | ((i6 << 15) & 458752)), composer8, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal8;
                    i22 = i20;
                    composer2 = composer8;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList12 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList12);
                        composerStartRestartGroup.updateRememberedValue(arrayList12);
                        obj = arrayList12;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList13 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList13);
                        composerStartRestartGroup.updateRememberedValue(arrayList13);
                        obj = arrayList13;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i213 = i6 >> 6;
                    Arrangement.Horizontal horizontal9 = start;
                    Composer composer9 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal9, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer10, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer10, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i214, FlowLineInfo flowLineInfo, Composer composer10, int i215) {
                            ComposerKt.sourceInformation(composer10, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i215, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i214), composer10, Integer.valueOf((i215 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i213 & 7168) | (i213 & 14) | 12582912 | (i213 & 112) | (i213 & 896) | ((i6 << 15) & 458752)), composer9, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal9;
                    i22 = i20;
                    composer2 = composer9;
                }
                vertical2 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier6 = modifier2;
                    final int i214 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer10, Integer num) {
                            invoke(composer10, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer10, int i215) {
                            ContextualFlowLayoutKt.ContextualFlowRow(i, modifier6, horizontal2, vertical2, i214, i22, contextualFlowRowOverflow2, function4, composer10, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            start = horizontal;
            i9 = i5 & 8;
            if (i9 != 0) {
                if ((i4 & 3072) == 0) {
                    top = vertical;
                    if (composerStartRestartGroup.changed(top)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i6 |= i10;
                }
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i6 |= i13;
                    }
                    i14 = i5 & 32;
                    if (i14 != 0) {
                        i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i6 |= i15;
                    }
                    i16 = i5 & 64;
                    if (i16 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i6 |= i17;
                    }
                    if ((i5 & 128) != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((4793491 & i6) == 4793490) {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i9 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList14 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList14);
                            composerStartRestartGroup.updateRememberedValue(arrayList14);
                            obj = arrayList14;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList15 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList15);
                            composerStartRestartGroup.updateRememberedValue(arrayList15);
                            obj = arrayList15;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i215 = i6 >> 6;
                        Arrangement.Horizontal horizontal10 = start;
                        Composer composer10 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal10, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer11, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer11, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i216, FlowLineInfo flowLineInfo, Composer composer11, int i217) {
                                ComposerKt.sourceInformation(composer11, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-8464804, i217, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                                }
                                function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i216), composer11, Integer.valueOf((i217 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i215 & 7168) | (i215 & 14) | 12582912 | (i215 & 112) | (i215 & 896) | ((i6 << 15) & 458752)), composer10, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal2 = horizontal10;
                        i22 = i20;
                        composer2 = composer10;
                    } else {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i9 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList16 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList16);
                            composerStartRestartGroup.updateRememberedValue(arrayList16);
                            obj = arrayList16;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList17 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList17);
                            composerStartRestartGroup.updateRememberedValue(arrayList17);
                            obj = arrayList17;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i216 = i6 >> 6;
                        Arrangement.Horizontal horizontal11 = start;
                        Composer composer11 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal11, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer12, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer12, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i217, FlowLineInfo flowLineInfo, Composer composer12, int i218) {
                                ComposerKt.sourceInformation(composer12, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-8464804, i218, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                                }
                                function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i217), composer12, Integer.valueOf((i218 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i216 & 7168) | (i216 & 14) | 12582912 | (i216 & 112) | (i216 & 896) | ((i6 << 15) & 458752)), composer11, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal2 = horizontal11;
                        i22 = i20;
                        composer2 = composer11;
                    }
                    vertical2 = top;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier7 = modifier2;
                        final int i217 = i19;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer12, Integer num) {
                                invoke(composer12, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer12, int i218) {
                                ContextualFlowLayoutKt.ContextualFlowRow(i, modifier7, horizontal2, vertical2, i217, i22, contextualFlowRowOverflow2, function4, composer12, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                            }
                        });
                    }
                }
                i6 |= 24576;
                i12 = i2;
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList18 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList18);
                        composerStartRestartGroup.updateRememberedValue(arrayList18);
                        obj = arrayList18;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList19 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList19);
                        composerStartRestartGroup.updateRememberedValue(arrayList19);
                        obj = arrayList19;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i218 = i6 >> 6;
                    Arrangement.Horizontal horizontal12 = start;
                    Composer composer12 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal12, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer13, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer13, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i219, FlowLineInfo flowLineInfo, Composer composer13, int i2110) {
                            ComposerKt.sourceInformation(composer13, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i2110, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i219), composer13, Integer.valueOf((i2110 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i218 & 7168) | (i218 & 14) | 12582912 | (i218 & 112) | (i218 & 896) | ((i6 << 15) & 458752)), composer12, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal12;
                    i22 = i20;
                    composer2 = composer12;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList110 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList110);
                        composerStartRestartGroup.updateRememberedValue(arrayList110);
                        obj = arrayList110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList111 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111);
                        composerStartRestartGroup.updateRememberedValue(arrayList111);
                        obj = arrayList111;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i219 = i6 >> 6;
                    Arrangement.Horizontal horizontal13 = start;
                    Composer composer13 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal13, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer14, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer14, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i2110, FlowLineInfo flowLineInfo, Composer composer14, int i2111) {
                            ComposerKt.sourceInformation(composer14, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i2111, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i2110), composer14, Integer.valueOf((i2111 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i219 & 7168) | (i219 & 14) | 12582912 | (i219 & 112) | (i219 & 896) | ((i6 << 15) & 458752)), composer13, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal13;
                    i22 = i20;
                    composer2 = composer13;
                }
                vertical2 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier8 = modifier2;
                    final int i2110 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer14, Integer num) {
                            invoke(composer14, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer14, int i2111) {
                            ContextualFlowLayoutKt.ContextualFlowRow(i, modifier8, horizontal2, vertical2, i2110, i22, contextualFlowRowOverflow2, function4, composer14, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= 3072;
            top = vertical;
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i6 |= i13;
                }
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList112 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList112);
                        composerStartRestartGroup.updateRememberedValue(arrayList112);
                        obj = arrayList112;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList113 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList113);
                        composerStartRestartGroup.updateRememberedValue(arrayList113);
                        obj = arrayList113;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2111 = i6 >> 6;
                    Arrangement.Horizontal horizontal14 = start;
                    Composer composer14 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal14, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer15, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer15, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i2112, FlowLineInfo flowLineInfo, Composer composer15, int i2113) {
                            ComposerKt.sourceInformation(composer15, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i2113, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i2112), composer15, Integer.valueOf((i2113 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2111 & 7168) | (i2111 & 14) | 12582912 | (i2111 & 112) | (i2111 & 896) | ((i6 << 15) & 458752)), composer14, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal14;
                    i22 = i20;
                    composer2 = composer14;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList114 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList114);
                        composerStartRestartGroup.updateRememberedValue(arrayList114);
                        obj = arrayList114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList115 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList115);
                        composerStartRestartGroup.updateRememberedValue(arrayList115);
                        obj = arrayList115;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2112 = i6 >> 6;
                    Arrangement.Horizontal horizontal15 = start;
                    Composer composer15 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal15, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer16, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer16, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i2113, FlowLineInfo flowLineInfo, Composer composer16, int i2114) {
                            ComposerKt.sourceInformation(composer16, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i2114, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i2113), composer16, Integer.valueOf((i2114 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2112 & 7168) | (i2112 & 14) | 12582912 | (i2112 & 112) | (i2112 & 896) | ((i6 << 15) & 458752)), composer15, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal15;
                    i22 = i20;
                    composer2 = composer15;
                }
                vertical2 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier9 = modifier2;
                    final int i2113 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer16, Integer num) {
                            invoke(composer16, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer16, int i2114) {
                            ContextualFlowLayoutKt.ContextualFlowRow(i, modifier9, horizontal2, vertical2, i2113, i22, contextualFlowRowOverflow2, function4, composer16, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= 24576;
            i12 = i2;
            i14 = i5 & 32;
            if (i14 != 0) {
                i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i6 |= i15;
            }
            i16 = i5 & 64;
            if (i16 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                    i17 = 1048576;
                } else {
                    i17 = 524288;
                }
                i6 |= i17;
            }
            if ((i5 & 128) != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((4793491 & i6) == 4793490) {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i9 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList116 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList116);
                    composerStartRestartGroup.updateRememberedValue(arrayList116);
                    obj = arrayList116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList117 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList117);
                    composerStartRestartGroup.updateRememberedValue(arrayList117);
                    obj = arrayList117;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2114 = i6 >> 6;
                Arrangement.Horizontal horizontal16 = start;
                Composer composer16 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal16, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer17, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer17, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i2115, FlowLineInfo flowLineInfo, Composer composer17, int i2116) {
                        ComposerKt.sourceInformation(composer17, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-8464804, i2116, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                        }
                        function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i2115), composer17, Integer.valueOf((i2116 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2114 & 7168) | (i2114 & 14) | 12582912 | (i2114 & 112) | (i2114 & 896) | ((i6 << 15) & 458752)), composer16, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal2 = horizontal16;
                i22 = i20;
                composer2 = composer16;
            } else {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i9 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList118 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList118);
                    composerStartRestartGroup.updateRememberedValue(arrayList118);
                    obj = arrayList118;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList119 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList119);
                    composerStartRestartGroup.updateRememberedValue(arrayList119);
                    obj = arrayList119;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2115 = i6 >> 6;
                Arrangement.Horizontal horizontal17 = start;
                Composer composer17 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal17, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer18, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer18, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i2116, FlowLineInfo flowLineInfo, Composer composer18, int i2117) {
                        ComposerKt.sourceInformation(composer18, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-8464804, i2117, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                        }
                        function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i2116), composer18, Integer.valueOf((i2117 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2115 & 7168) | (i2115 & 14) | 12582912 | (i2115 & 112) | (i2115 & 896) | ((i6 << 15) & 458752)), composer17, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal2 = horizontal17;
                i22 = i20;
                composer2 = composer17;
            }
            vertical2 = top;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier10 = modifier2;
                final int i2116 = i19;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer18, Integer num) {
                        invoke(composer18, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer18, int i2117) {
                        ContextualFlowLayoutKt.ContextualFlowRow(i, modifier10, horizontal2, vertical2, i2116, i22, contextualFlowRowOverflow2, function4, composer18, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                    }
                });
            }
        }
        i6 |= 48;
        modifier2 = modifier;
        i7 = i5 & 4;
        if (i7 != 0) {
            if ((i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                start = horizontal;
                if (composerStartRestartGroup.changed(start)) {
                    i8 = 256;
                } else {
                    i8 = 128;
                }
                i6 |= i8;
            }
            i9 = i5 & 8;
            if (i9 != 0) {
                if ((i4 & 3072) == 0) {
                    top = vertical;
                    if (composerStartRestartGroup.changed(top)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i6 |= i10;
                }
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i6 |= i13;
                    }
                    i14 = i5 & 32;
                    if (i14 != 0) {
                        i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i6 |= i15;
                    }
                    i16 = i5 & 64;
                    if (i16 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i6 |= i17;
                    }
                    if ((i5 & 128) != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((4793491 & i6) == 4793490) {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i9 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList1110 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1110);
                            composerStartRestartGroup.updateRememberedValue(arrayList1110);
                            obj = arrayList1110;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList1111 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111);
                            composerStartRestartGroup.updateRememberedValue(arrayList1111);
                            obj = arrayList1111;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2117 = i6 >> 6;
                        Arrangement.Horizontal horizontal18 = start;
                        Composer composer18 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal18, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer19, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer19, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i2118, FlowLineInfo flowLineInfo, Composer composer19, int i2119) {
                                ComposerKt.sourceInformation(composer19, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-8464804, i2119, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                                }
                                function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i2118), composer19, Integer.valueOf((i2119 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2117 & 7168) | (i2117 & 14) | 12582912 | (i2117 & 112) | (i2117 & 896) | ((i6 << 15) & 458752)), composer18, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal2 = horizontal18;
                        i22 = i20;
                        composer2 = composer18;
                    } else {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i9 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList1112 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1112);
                            composerStartRestartGroup.updateRememberedValue(arrayList1112);
                            obj = arrayList1112;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList1113 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1113);
                            composerStartRestartGroup.updateRememberedValue(arrayList1113);
                            obj = arrayList1113;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2118 = i6 >> 6;
                        Arrangement.Horizontal horizontal19 = start;
                        Composer composer19 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal19, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer110, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer110, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i2119, FlowLineInfo flowLineInfo, Composer composer110, int i21110) {
                                ComposerKt.sourceInformation(composer110, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-8464804, i21110, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                                }
                                function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i2119), composer110, Integer.valueOf((i21110 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2118 & 7168) | (i2118 & 14) | 12582912 | (i2118 & 112) | (i2118 & 896) | ((i6 << 15) & 458752)), composer19, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal2 = horizontal19;
                        i22 = i20;
                        composer2 = composer19;
                    }
                    vertical2 = top;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier11 = modifier2;
                        final int i2119 = i19;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer110, Integer num) {
                                invoke(composer110, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer110, int i21110) {
                                ContextualFlowLayoutKt.ContextualFlowRow(i, modifier11, horizontal2, vertical2, i2119, i22, contextualFlowRowOverflow2, function4, composer110, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                            }
                        });
                    }
                }
                i6 |= 24576;
                i12 = i2;
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1114 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1114);
                        composerStartRestartGroup.updateRememberedValue(arrayList1114);
                        obj = arrayList1114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1115 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1115);
                        composerStartRestartGroup.updateRememberedValue(arrayList1115);
                        obj = arrayList1115;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21110 = i6 >> 6;
                    Arrangement.Horizontal horizontal110 = start;
                    Composer composer110 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal110, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer111, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer111, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i21111, FlowLineInfo flowLineInfo, Composer composer111, int i21112) {
                            ComposerKt.sourceInformation(composer111, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i21112, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i21111), composer111, Integer.valueOf((i21112 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21110 & 7168) | (i21110 & 14) | 12582912 | (i21110 & 112) | (i21110 & 896) | ((i6 << 15) & 458752)), composer110, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal110;
                    i22 = i20;
                    composer2 = composer110;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1116 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1116);
                        composerStartRestartGroup.updateRememberedValue(arrayList1116);
                        obj = arrayList1116;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1117 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1117);
                        composerStartRestartGroup.updateRememberedValue(arrayList1117);
                        obj = arrayList1117;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21111 = i6 >> 6;
                    Arrangement.Horizontal horizontal111 = start;
                    Composer composer111 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal111, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer112, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer112, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i21112, FlowLineInfo flowLineInfo, Composer composer112, int i21113) {
                            ComposerKt.sourceInformation(composer112, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i21113, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i21112), composer112, Integer.valueOf((i21113 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21111 & 7168) | (i21111 & 14) | 12582912 | (i21111 & 112) | (i21111 & 896) | ((i6 << 15) & 458752)), composer111, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal111;
                    i22 = i20;
                    composer2 = composer111;
                }
                vertical2 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier12 = modifier2;
                    final int i21112 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer112, Integer num) {
                            invoke(composer112, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer112, int i21113) {
                            ContextualFlowLayoutKt.ContextualFlowRow(i, modifier12, horizontal2, vertical2, i21112, i22, contextualFlowRowOverflow2, function4, composer112, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= 3072;
            top = vertical;
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i6 |= i13;
                }
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1118 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1118);
                        composerStartRestartGroup.updateRememberedValue(arrayList1118);
                        obj = arrayList1118;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1119 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1119);
                        composerStartRestartGroup.updateRememberedValue(arrayList1119);
                        obj = arrayList1119;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21113 = i6 >> 6;
                    Arrangement.Horizontal horizontal112 = start;
                    Composer composer112 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal112, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer113, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer113, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i21114, FlowLineInfo flowLineInfo, Composer composer113, int i21115) {
                            ComposerKt.sourceInformation(composer113, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i21115, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i21114), composer113, Integer.valueOf((i21115 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21113 & 7168) | (i21113 & 14) | 12582912 | (i21113 & 112) | (i21113 & 896) | ((i6 << 15) & 458752)), composer112, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal112;
                    i22 = i20;
                    composer2 = composer112;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11110 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11110);
                        composerStartRestartGroup.updateRememberedValue(arrayList11110);
                        obj = arrayList11110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11111 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11111);
                        composerStartRestartGroup.updateRememberedValue(arrayList11111);
                        obj = arrayList11111;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21114 = i6 >> 6;
                    Arrangement.Horizontal horizontal113 = start;
                    Composer composer113 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal113, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer114, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer114, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i21115, FlowLineInfo flowLineInfo, Composer composer114, int i21116) {
                            ComposerKt.sourceInformation(composer114, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i21116, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i21115), composer114, Integer.valueOf((i21116 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21114 & 7168) | (i21114 & 14) | 12582912 | (i21114 & 112) | (i21114 & 896) | ((i6 << 15) & 458752)), composer113, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal113;
                    i22 = i20;
                    composer2 = composer113;
                }
                vertical2 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier13 = modifier2;
                    final int i21115 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer114, Integer num) {
                            invoke(composer114, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer114, int i21116) {
                            ContextualFlowLayoutKt.ContextualFlowRow(i, modifier13, horizontal2, vertical2, i21115, i22, contextualFlowRowOverflow2, function4, composer114, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= 24576;
            i12 = i2;
            i14 = i5 & 32;
            if (i14 != 0) {
                i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i6 |= i15;
            }
            i16 = i5 & 64;
            if (i16 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                    i17 = 1048576;
                } else {
                    i17 = 524288;
                }
                i6 |= i17;
            }
            if ((i5 & 128) != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((4793491 & i6) == 4793490) {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i9 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList11112 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11112);
                    composerStartRestartGroup.updateRememberedValue(arrayList11112);
                    obj = arrayList11112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList11113 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11113);
                    composerStartRestartGroup.updateRememberedValue(arrayList11113);
                    obj = arrayList11113;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i21116 = i6 >> 6;
                Arrangement.Horizontal horizontal114 = start;
                Composer composer114 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal114, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer115, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer115, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i21117, FlowLineInfo flowLineInfo, Composer composer115, int i21118) {
                        ComposerKt.sourceInformation(composer115, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-8464804, i21118, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                        }
                        function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i21117), composer115, Integer.valueOf((i21118 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21116 & 7168) | (i21116 & 14) | 12582912 | (i21116 & 112) | (i21116 & 896) | ((i6 << 15) & 458752)), composer114, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal2 = horizontal114;
                i22 = i20;
                composer2 = composer114;
            } else {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i9 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList11114 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11114);
                    composerStartRestartGroup.updateRememberedValue(arrayList11114);
                    obj = arrayList11114;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList11115 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11115);
                    composerStartRestartGroup.updateRememberedValue(arrayList11115);
                    obj = arrayList11115;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i21117 = i6 >> 6;
                Arrangement.Horizontal horizontal115 = start;
                Composer composer115 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal115, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer116, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer116, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i21118, FlowLineInfo flowLineInfo, Composer composer116, int i21119) {
                        ComposerKt.sourceInformation(composer116, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-8464804, i21119, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                        }
                        function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i21118), composer116, Integer.valueOf((i21119 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21117 & 7168) | (i21117 & 14) | 12582912 | (i21117 & 112) | (i21117 & 896) | ((i6 << 15) & 458752)), composer115, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal2 = horizontal115;
                i22 = i20;
                composer2 = composer115;
            }
            vertical2 = top;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier14 = modifier2;
                final int i21118 = i19;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer116, Integer num) {
                        invoke(composer116, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer116, int i21119) {
                        ContextualFlowLayoutKt.ContextualFlowRow(i, modifier14, horizontal2, vertical2, i21118, i22, contextualFlowRowOverflow2, function4, composer116, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                    }
                });
            }
        }
        i6 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        start = horizontal;
        i9 = i5 & 8;
        if (i9 != 0) {
            if ((i4 & 3072) == 0) {
                top = vertical;
                if (composerStartRestartGroup.changed(top)) {
                    i10 = 2048;
                } else {
                    i10 = 1024;
                }
                i6 |= i10;
            }
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i6 |= i13;
                }
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11116 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11116);
                        composerStartRestartGroup.updateRememberedValue(arrayList11116);
                        obj = arrayList11116;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11117 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11117);
                        composerStartRestartGroup.updateRememberedValue(arrayList11117);
                        obj = arrayList11117;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21119 = i6 >> 6;
                    Arrangement.Horizontal horizontal116 = start;
                    Composer composer116 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal116, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer117, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer117, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i211110, FlowLineInfo flowLineInfo, Composer composer117, int i211111) {
                            ComposerKt.sourceInformation(composer117, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i211111, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i211110), composer117, Integer.valueOf((i211111 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21119 & 7168) | (i21119 & 14) | 12582912 | (i21119 & 112) | (i21119 & 896) | ((i6 << 15) & 458752)), composer116, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal116;
                    i22 = i20;
                    composer2 = composer116;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i9 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11118 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11118);
                        composerStartRestartGroup.updateRememberedValue(arrayList11118);
                        obj = arrayList11118;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11119 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11119);
                        composerStartRestartGroup.updateRememberedValue(arrayList11119);
                        obj = arrayList11119;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211110 = i6 >> 6;
                    Arrangement.Horizontal horizontal117 = start;
                    Composer composer117 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal117, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer118, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer118, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i211111, FlowLineInfo flowLineInfo, Composer composer118, int i211112) {
                            ComposerKt.sourceInformation(composer118, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-8464804, i211112, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                            }
                            function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i211111), composer118, Integer.valueOf((i211112 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211110 & 7168) | (i211110 & 14) | 12582912 | (i211110 & 112) | (i211110 & 896) | ((i6 << 15) & 458752)), composer117, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal2 = horizontal117;
                    i22 = i20;
                    composer2 = composer117;
                }
                vertical2 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier15 = modifier2;
                    final int i211111 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer118, Integer num) {
                            invoke(composer118, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer118, int i211112) {
                            ContextualFlowLayoutKt.ContextualFlowRow(i, modifier15, horizontal2, vertical2, i211111, i22, contextualFlowRowOverflow2, function4, composer118, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= 24576;
            i12 = i2;
            i14 = i5 & 32;
            if (i14 != 0) {
                i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i6 |= i15;
            }
            i16 = i5 & 64;
            if (i16 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                    i17 = 1048576;
                } else {
                    i17 = 524288;
                }
                i6 |= i17;
            }
            if ((i5 & 128) != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((4793491 & i6) == 4793490) {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i9 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111110 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111110);
                    composerStartRestartGroup.updateRememberedValue(arrayList111110);
                    obj = arrayList111110;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111111 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111111);
                    composerStartRestartGroup.updateRememberedValue(arrayList111111);
                    obj = arrayList111111;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i211112 = i6 >> 6;
                Arrangement.Horizontal horizontal118 = start;
                Composer composer118 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal118, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer119, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer119, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i211113, FlowLineInfo flowLineInfo, Composer composer119, int i211114) {
                        ComposerKt.sourceInformation(composer119, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-8464804, i211114, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                        }
                        function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i211113), composer119, Integer.valueOf((i211114 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211112 & 7168) | (i211112 & 14) | 12582912 | (i211112 & 112) | (i211112 & 896) | ((i6 << 15) & 458752)), composer118, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal2 = horizontal118;
                i22 = i20;
                composer2 = composer118;
            } else {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i9 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111112 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111112);
                    composerStartRestartGroup.updateRememberedValue(arrayList111112);
                    obj = arrayList111112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111113 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111113);
                    composerStartRestartGroup.updateRememberedValue(arrayList111113);
                    obj = arrayList111113;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i211113 = i6 >> 6;
                Arrangement.Horizontal horizontal119 = start;
                Composer composer119 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal119, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer1110, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer1110, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i211114, FlowLineInfo flowLineInfo, Composer composer1110, int i211115) {
                        ComposerKt.sourceInformation(composer1110, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-8464804, i211115, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                        }
                        function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i211114), composer1110, Integer.valueOf((i211115 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211113 & 7168) | (i211113 & 14) | 12582912 | (i211113 & 112) | (i211113 & 896) | ((i6 << 15) & 458752)), composer119, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal2 = horizontal119;
                i22 = i20;
                composer2 = composer119;
            }
            vertical2 = top;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier16 = modifier2;
                final int i211114 = i19;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer1110, Integer num) {
                        invoke(composer1110, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer1110, int i211115) {
                        ContextualFlowLayoutKt.ContextualFlowRow(i, modifier16, horizontal2, vertical2, i211114, i22, contextualFlowRowOverflow2, function4, composer1110, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                    }
                });
            }
        }
        i6 |= 3072;
        top = vertical;
        i11 = i5 & 16;
        if (i11 != 0) {
            if ((i4 & 24576) == 0) {
                i12 = i2;
                if (composerStartRestartGroup.changed(i12)) {
                    i13 = 16384;
                } else {
                    i13 = 8192;
                }
                i6 |= i13;
            }
            i14 = i5 & 32;
            if (i14 != 0) {
                i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i6 |= i15;
            }
            i16 = i5 & 64;
            if (i16 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                    i17 = 1048576;
                } else {
                    i17 = 524288;
                }
                i6 |= i17;
            }
            if ((i5 & 128) != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((4793491 & i6) == 4793490) {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i9 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111114 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111114);
                    composerStartRestartGroup.updateRememberedValue(arrayList111114);
                    obj = arrayList111114;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111115 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111115);
                    composerStartRestartGroup.updateRememberedValue(arrayList111115);
                    obj = arrayList111115;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i211115 = i6 >> 6;
                Arrangement.Horizontal horizontal1110 = start;
                Composer composer1110 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal1110, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer1111, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer1111, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i211116, FlowLineInfo flowLineInfo, Composer composer1111, int i211117) {
                        ComposerKt.sourceInformation(composer1111, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-8464804, i211117, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                        }
                        function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i211116), composer1111, Integer.valueOf((i211117 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211115 & 7168) | (i211115 & 14) | 12582912 | (i211115 & 112) | (i211115 & 896) | ((i6 << 15) & 458752)), composer1110, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal2 = horizontal1110;
                i22 = i20;
                composer2 = composer1110;
            } else {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i9 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111116 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111116);
                    composerStartRestartGroup.updateRememberedValue(arrayList111116);
                    obj = arrayList111116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111117 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111117);
                    composerStartRestartGroup.updateRememberedValue(arrayList111117);
                    obj = arrayList111117;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i211116 = i6 >> 6;
                Arrangement.Horizontal horizontal1111 = start;
                Composer composer1111 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal1111, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer1112, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer1112, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i211117, FlowLineInfo flowLineInfo, Composer composer1112, int i211118) {
                        ComposerKt.sourceInformation(composer1112, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-8464804, i211118, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                        }
                        function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i211117), composer1112, Integer.valueOf((i211118 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211116 & 7168) | (i211116 & 14) | 12582912 | (i211116 & 112) | (i211116 & 896) | ((i6 << 15) & 458752)), composer1111, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal2 = horizontal1111;
                i22 = i20;
                composer2 = composer1111;
            }
            vertical2 = top;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier17 = modifier2;
                final int i211117 = i19;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer1112, Integer num) {
                        invoke(composer1112, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer1112, int i211118) {
                        ContextualFlowLayoutKt.ContextualFlowRow(i, modifier17, horizontal2, vertical2, i211117, i22, contextualFlowRowOverflow2, function4, composer1112, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                    }
                });
            }
        }
        i6 |= 24576;
        i12 = i2;
        i14 = i5 & 32;
        if (i14 != 0) {
            i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changed(i3)) {
                i15 = 131072;
            } else {
                i15 = 65536;
            }
            i6 |= i15;
        }
        i16 = i5 & 64;
        if (i16 != 0) {
            i6 |= 1572864;
        } else if ((i4 & 1572864) == 0) {
            if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                i17 = 1048576;
            } else {
                i17 = 524288;
            }
            i6 |= i17;
        }
        if ((i5 & 128) != 0) {
            i6 |= 12582912;
        } else if ((i4 & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function4)) {
                i18 = 8388608;
            } else {
                i18 = 4194304;
            }
            i6 |= i18;
        }
        if ((4793491 & i6) == 4793490) {
            if (i23 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i7 != 0) {
                start = Arrangement.INSTANCE.getStart();
            }
            if (i9 != 0) {
                top = Arrangement.INSTANCE.getTop();
            }
            if (i11 != 0) {
                i19 = Integer.MAX_VALUE;
            } else {
                i19 = i12;
            }
            if (i14 != 0) {
                i20 = Integer.MAX_VALUE;
            } else {
                i20 = i3;
            }
            if (i16 != 0) {
                clip = ContextualFlowRowOverflow.INSTANCE.getClip();
            } else {
                clip = contextualFlowRowOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
            i21 = 3670016 & i6;
            if (i21 == 1048576) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
            if (i21 == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z2) {
                obj = objRememberedValue2;
                ArrayList arrayList111118 = new ArrayList();
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111118);
                composerStartRestartGroup.updateRememberedValue(arrayList111118);
                obj = arrayList111118;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList111119 = new ArrayList();
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111119);
                composerStartRestartGroup.updateRememberedValue(arrayList111119);
                obj = arrayList111119;
            }
            obj = objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i211118 = i6 >> 6;
            Arrangement.Horizontal horizontal1112 = start;
            Composer composer1112 = composerStartRestartGroup;
            SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal1112, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer1113, Integer num2) {
                    invoke(num.intValue(), flowLineInfo, composer1113, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i211119, FlowLineInfo flowLineInfo, Composer composer1113, int i2111110) {
                    ComposerKt.sourceInformation(composer1113, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-8464804, i2111110, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                    }
                    function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i211119), composer1113, Integer.valueOf((i2111110 << 3) & 112));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211118 & 7168) | (i211118 & 14) | 12582912 | (i211118 & 112) | (i211118 & 896) | ((i6 << 15) & 458752)), composer1112, (i6 >> 3) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contextualFlowRowOverflow2 = clip;
            horizontal2 = horizontal1112;
            i22 = i20;
            composer2 = composer1112;
        } else {
            if (i23 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i7 != 0) {
                start = Arrangement.INSTANCE.getStart();
            }
            if (i9 != 0) {
                top = Arrangement.INSTANCE.getTop();
            }
            if (i11 != 0) {
                i19 = Integer.MAX_VALUE;
            } else {
                i19 = i12;
            }
            if (i14 != 0) {
                i20 = Integer.MAX_VALUE;
            } else {
                i20 = i3;
            }
            if (i16 != 0) {
                clip = ContextualFlowRowOverflow.INSTANCE.getClip();
            } else {
                clip = contextualFlowRowOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-341770689, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:73)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235053714, "CC(remember):ContextualFlowLayout.kt#9igjgp");
            i21 = 3670016 & i6;
            if (i21 == 1048576) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1235057394, "CC(remember):ContextualFlowLayout.kt#9igjgp");
            if (i21 == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z2) {
                obj = objRememberedValue2;
                ArrayList arrayList1111110 = new ArrayList();
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111110);
                composerStartRestartGroup.updateRememberedValue(arrayList1111110);
                obj = arrayList1111110;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList1111111 = new ArrayList();
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111111);
                composerStartRestartGroup.updateRememberedValue(arrayList1111111);
                obj = arrayList1111111;
            }
            obj = objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i211119 = i6 >> 6;
            Arrangement.Horizontal horizontal1113 = start;
            Composer composer1113 = composerStartRestartGroup;
            SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(horizontal1113, top, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-8464804, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowRow$measurePolicy$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer1114, Integer num2) {
                    invoke(num.intValue(), flowLineInfo, composer1114, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i2111110, FlowLineInfo flowLineInfo, Composer composer1114, int i2111111) {
                    ComposerKt.sourceInformation(composer1114, "C97@4208L14:ContextualFlowLayout.kt#2w3rfo");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-8464804, i2111111, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:91)");
                    }
                    function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i2111110), composer1114, Integer.valueOf((i2111111 << 3) & 112));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211119 & 7168) | (i211119 & 14) | 12582912 | (i211119 & 112) | (i211119 & 896) | ((i6 << 15) & 458752)), composer1113, (i6 >> 3) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contextualFlowRowOverflow2 = clip;
            horizontal2 = horizontal1113;
            i22 = i20;
            composer2 = composer1113;
        }
        vertical2 = top;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier18 = modifier2;
            final int i2111110 = i19;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer1114, Integer num) {
                    invoke(composer1114, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer1114, int i2111111) {
                    ContextualFlowLayoutKt.ContextualFlowRow(i, modifier18, horizontal2, vertical2, i2111110, i22, contextualFlowRowOverflow2, function4, composer1114, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0132  */
    /* JADX WARN: Code duplicated, block: B:103:0x0134  */
    /* JADX WARN: Code duplicated, block: B:105:0x0137  */
    /* JADX WARN: Code duplicated, block: B:106:0x0139  */
    /* JADX WARN: Code duplicated, block: B:108:0x013d  */
    /* JADX WARN: Code duplicated, block: B:109:0x0144  */
    /* JADX WARN: Code duplicated, block: B:112:0x014c  */
    /* JADX WARN: Code duplicated, block: B:115:0x0165  */
    /* JADX WARN: Code duplicated, block: B:116:0x0167  */
    /* JADX WARN: Code duplicated, block: B:119:0x016e  */
    /* JADX WARN: Code duplicated, block: B:121:0x0176  */
    /* JADX WARN: Code duplicated, block: B:124:0x018d  */
    /* JADX WARN: Code duplicated, block: B:125:0x018f  */
    /* JADX WARN: Code duplicated, block: B:128:0x0196  */
    /* JADX WARN: Code duplicated, block: B:130:0x019e  */
    /* JADX WARN: Code duplicated, block: B:133:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:137:0x0202  */
    /* JADX WARN: Code duplicated, block: B:139:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0052  */
    /* JADX WARN: Code duplicated, block: B:27:0x0055  */
    /* JADX WARN: Code duplicated, block: B:29:0x0059  */
    /* JADX WARN: Code duplicated, block: B:31:0x0061  */
    /* JADX WARN: Code duplicated, block: B:32:0x0064  */
    /* JADX WARN: Code duplicated, block: B:37:0x006e  */
    /* JADX WARN: Code duplicated, block: B:38:0x0071  */
    /* JADX WARN: Code duplicated, block: B:40:0x0075  */
    /* JADX WARN: Code duplicated, block: B:42:0x007d  */
    /* JADX WARN: Code duplicated, block: B:43:0x0080  */
    /* JADX WARN: Code duplicated, block: B:48:0x008a  */
    /* JADX WARN: Code duplicated, block: B:49:0x008d  */
    /* JADX WARN: Code duplicated, block: B:51:0x0091  */
    /* JADX WARN: Code duplicated, block: B:53:0x0099  */
    /* JADX WARN: Code duplicated, block: B:54:0x009c  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:60:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:65:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:70:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00da  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:82:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:89:0x0100  */
    /* JADX WARN: Code duplicated, block: B:94:0x0114 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x0116  */
    /* JADX WARN: Code duplicated, block: B:97:0x011d  */
    /* JADX WARN: Code duplicated, block: B:99:0x0126  */
    public static final void ContextualFlowColumn(final int i, Modifier modifier, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i2, int i3, ContextualFlowColumnOverflow contextualFlowColumnOverflow, final Function4<? super ContextualFlowColumnScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i4, final int i5) {
        int i6;
        Modifier modifier2;
        int i7;
        Arrangement.Vertical top;
        int i8;
        int i9;
        Arrangement.Horizontal start;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        ContextualFlowColumnOverflow clip;
        int i21;
        boolean z;
        Object objRememberedValue;
        FlowLayoutOverflowState flowLayoutOverflowState;
        boolean z2;
        Object objRememberedValue2;
        Object obj;
        final ContextualFlowColumnOverflow contextualFlowColumnOverflow2;
        final Arrangement.Vertical vertical2;
        final int i22;
        Composer composer2;
        final Arrangement.Horizontal horizontal2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-588476895);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ContextualFlowColumn)P(2,5,7,1,3,4,6)149@6671L65,152@6782L193,165@7201L270,157@7000L471,175@7477L90:ContextualFlowLayout.kt#2w3rfo");
        if ((i5 & 1) != 0) {
            i6 = i4 | 6;
        } else if ((i4 & 6) == 0) {
            i6 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i4;
        } else {
            i6 = i4;
        }
        int i23 = i5 & 2;
        if (i23 == 0) {
            if ((i4 & 48) == 0) {
                modifier2 = modifier;
                i6 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i7 = i5 & 4;
            if (i7 != 0) {
                if ((i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    top = vertical;
                    if (composerStartRestartGroup.changed(top)) {
                        i8 = 256;
                    } else {
                        i8 = 128;
                    }
                    i6 |= i8;
                }
                i9 = i5 & 8;
                if (i9 != 0) {
                    if ((i4 & 3072) == 0) {
                        start = horizontal;
                        if (composerStartRestartGroup.changed(start)) {
                            i10 = 2048;
                        } else {
                            i10 = 1024;
                        }
                        i6 |= i10;
                    }
                    i11 = i5 & 16;
                    if (i11 != 0) {
                        if ((i4 & 24576) == 0) {
                            i12 = i2;
                            if (composerStartRestartGroup.changed(i12)) {
                                i13 = 16384;
                            } else {
                                i13 = 8192;
                            }
                            i6 |= i13;
                        }
                        i14 = i5 & 32;
                        if (i14 != 0) {
                            i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(i3)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i6 |= i15;
                        }
                        i16 = i5 & 64;
                        if (i16 != 0) {
                            i6 |= 1572864;
                        } else if ((i4 & 1572864) == 0) {
                            if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i6 |= i17;
                        }
                        if ((i5 & 128) != 0) {
                            i6 |= 12582912;
                        } else if ((i4 & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i18 = 8388608;
                            } else {
                                i18 = 4194304;
                            }
                            i6 |= i18;
                        }
                        if ((4793491 & i6) == 4793490 || !composerStartRestartGroup.getSkipping()) {
                            if (i23 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            }
                            if (i9 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            }
                            if (i11 != 0) {
                                i19 = Integer.MAX_VALUE;
                            } else {
                                i19 = i12;
                            }
                            if (i14 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i3;
                            }
                            if (i16 != 0) {
                                clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                            } else {
                                clip = contextualFlowColumnOverflow;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                            i21 = 3670016 & i6;
                            if (i21 == 1048576) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                            if (i21 == 1048576) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                obj = objRememberedValue2;
                                ArrayList arrayList = new ArrayList();
                                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList);
                                composerStartRestartGroup.updateRememberedValue(arrayList);
                                obj = arrayList;
                            }
                            obj = objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i24 = i6 >> 6;
                            Arrangement.Vertical vertical3 = top;
                            Composer composer3 = composerStartRestartGroup;
                            SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical3, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(4);
                                }

                                @Override // kotlin.jvm.functions.Function4
                                public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer4, Integer num2) {
                                    invoke(num.intValue(), flowLineInfo, composer4, num2.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(int i25, FlowLineInfo flowLineInfo, Composer composer4, int i26) {
                                    ComposerKt.sourceInformation(composer4, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(302435318, i26, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                                    }
                                    function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i25), composer4, Integer.valueOf((i26 << 3) & 112));
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i24 & 7168) | (i24 & 14) | 12582912 | (i24 & 112) | (i24 & 896) | ((i6 << 15) & 458752)), composer3, (i6 >> 3) & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            contextualFlowColumnOverflow2 = clip;
                            vertical2 = vertical3;
                            i22 = i20;
                            composer2 = composer3;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            i22 = i3;
                            composer2 = composerStartRestartGroup;
                            vertical2 = top;
                            i19 = i12;
                            contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                        }
                        horizontal2 = start;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            final Modifier modifier3 = modifier2;
                            final int i25 = i19;
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
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

                                public final void invoke(Composer composer4, int i26) {
                                    ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier3, vertical2, horizontal2, i25, i22, contextualFlowColumnOverflow2, function4, composer4, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                                }
                            });
                        }
                    }
                    i6 |= 24576;
                    i12 = i2;
                    i14 = i5 & 32;
                    if (i14 != 0) {
                        i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i6 |= i15;
                    }
                    i16 = i5 & 64;
                    if (i16 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i6 |= i17;
                    }
                    if ((i5 & 128) != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((4793491 & i6) == 4793490) {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i9 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList2 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList2);
                            composerStartRestartGroup.updateRememberedValue(arrayList2);
                            obj = arrayList2;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList3 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList3);
                            composerStartRestartGroup.updateRememberedValue(arrayList3);
                            obj = arrayList3;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i26 = i6 >> 6;
                        Arrangement.Vertical vertical4 = top;
                        Composer composer4 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical4, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer5, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer5, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i27, FlowLineInfo flowLineInfo, Composer composer5, int i28) {
                                ComposerKt.sourceInformation(composer5, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(302435318, i28, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                                }
                                function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i27), composer5, Integer.valueOf((i28 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i26 & 7168) | (i26 & 14) | 12582912 | (i26 & 112) | (i26 & 896) | ((i6 << 15) & 458752)), composer4, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical2 = vertical4;
                        i22 = i20;
                        composer2 = composer4;
                    } else {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i9 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList4 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList4);
                            composerStartRestartGroup.updateRememberedValue(arrayList4);
                            obj = arrayList4;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList5 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList5);
                            composerStartRestartGroup.updateRememberedValue(arrayList5);
                            obj = arrayList5;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i27 = i6 >> 6;
                        Arrangement.Vertical vertical5 = top;
                        Composer composer5 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical5, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer6, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer6, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i28, FlowLineInfo flowLineInfo, Composer composer6, int i29) {
                                ComposerKt.sourceInformation(composer6, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(302435318, i29, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                                }
                                function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i28), composer6, Integer.valueOf((i29 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i27 & 7168) | (i27 & 14) | 12582912 | (i27 & 112) | (i27 & 896) | ((i6 << 15) & 458752)), composer5, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical2 = vertical5;
                        i22 = i20;
                        composer2 = composer5;
                    }
                    horizontal2 = start;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier4 = modifier2;
                        final int i28 = i19;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer6, Integer num) {
                                invoke(composer6, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer6, int i29) {
                                ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier4, vertical2, horizontal2, i28, i22, contextualFlowColumnOverflow2, function4, composer6, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                            }
                        });
                    }
                }
                i6 |= 3072;
                start = horizontal;
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i6 |= i13;
                    }
                    i14 = i5 & 32;
                    if (i14 != 0) {
                        i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i6 |= i15;
                    }
                    i16 = i5 & 64;
                    if (i16 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i6 |= i17;
                    }
                    if ((i5 & 128) != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((4793491 & i6) == 4793490) {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i9 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList6 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList6);
                            composerStartRestartGroup.updateRememberedValue(arrayList6);
                            obj = arrayList6;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList7 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList7);
                            composerStartRestartGroup.updateRememberedValue(arrayList7);
                            obj = arrayList7;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i29 = i6 >> 6;
                        Arrangement.Vertical vertical6 = top;
                        Composer composer6 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical6, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer7, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer7, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i210, FlowLineInfo flowLineInfo, Composer composer7, int i211) {
                                ComposerKt.sourceInformation(composer7, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(302435318, i211, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                                }
                                function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i210), composer7, Integer.valueOf((i211 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i29 & 7168) | (i29 & 14) | 12582912 | (i29 & 112) | (i29 & 896) | ((i6 << 15) & 458752)), composer6, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical2 = vertical6;
                        i22 = i20;
                        composer2 = composer6;
                    } else {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i9 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList8 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList8);
                            composerStartRestartGroup.updateRememberedValue(arrayList8);
                            obj = arrayList8;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList9 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList9);
                            composerStartRestartGroup.updateRememberedValue(arrayList9);
                            obj = arrayList9;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i210 = i6 >> 6;
                        Arrangement.Vertical vertical7 = top;
                        Composer composer7 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical7, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer8, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer8, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i211, FlowLineInfo flowLineInfo, Composer composer8, int i212) {
                                ComposerKt.sourceInformation(composer8, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(302435318, i212, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                                }
                                function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i211), composer8, Integer.valueOf((i212 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i210 & 7168) | (i210 & 14) | 12582912 | (i210 & 112) | (i210 & 896) | ((i6 << 15) & 458752)), composer7, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical2 = vertical7;
                        i22 = i20;
                        composer2 = composer7;
                    }
                    horizontal2 = start;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier5 = modifier2;
                        final int i211 = i19;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer8, Integer num) {
                                invoke(composer8, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer8, int i212) {
                                ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier5, vertical2, horizontal2, i211, i22, contextualFlowColumnOverflow2, function4, composer8, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                            }
                        });
                    }
                }
                i6 |= 24576;
                i12 = i2;
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList10 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList10);
                        composerStartRestartGroup.updateRememberedValue(arrayList10);
                        obj = arrayList10;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11);
                        composerStartRestartGroup.updateRememberedValue(arrayList11);
                        obj = arrayList11;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i212 = i6 >> 6;
                    Arrangement.Vertical vertical8 = top;
                    Composer composer8 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical8, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer9, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer9, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i213, FlowLineInfo flowLineInfo, Composer composer9, int i214) {
                            ComposerKt.sourceInformation(composer9, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i214, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i213), composer9, Integer.valueOf((i214 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i212 & 7168) | (i212 & 14) | 12582912 | (i212 & 112) | (i212 & 896) | ((i6 << 15) & 458752)), composer8, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical8;
                    i22 = i20;
                    composer2 = composer8;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList12 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList12);
                        composerStartRestartGroup.updateRememberedValue(arrayList12);
                        obj = arrayList12;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList13 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList13);
                        composerStartRestartGroup.updateRememberedValue(arrayList13);
                        obj = arrayList13;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i213 = i6 >> 6;
                    Arrangement.Vertical vertical9 = top;
                    Composer composer9 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical9, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer10, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer10, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i214, FlowLineInfo flowLineInfo, Composer composer10, int i215) {
                            ComposerKt.sourceInformation(composer10, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i215, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i214), composer10, Integer.valueOf((i215 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i213 & 7168) | (i213 & 14) | 12582912 | (i213 & 112) | (i213 & 896) | ((i6 << 15) & 458752)), composer9, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical9;
                    i22 = i20;
                    composer2 = composer9;
                }
                horizontal2 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier6 = modifier2;
                    final int i214 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer10, Integer num) {
                            invoke(composer10, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer10, int i215) {
                            ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier6, vertical2, horizontal2, i214, i22, contextualFlowColumnOverflow2, function4, composer10, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            top = vertical;
            i9 = i5 & 8;
            if (i9 != 0) {
                if ((i4 & 3072) == 0) {
                    start = horizontal;
                    if (composerStartRestartGroup.changed(start)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i6 |= i10;
                }
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i6 |= i13;
                    }
                    i14 = i5 & 32;
                    if (i14 != 0) {
                        i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i6 |= i15;
                    }
                    i16 = i5 & 64;
                    if (i16 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i6 |= i17;
                    }
                    if ((i5 & 128) != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((4793491 & i6) == 4793490) {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i9 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList14 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList14);
                            composerStartRestartGroup.updateRememberedValue(arrayList14);
                            obj = arrayList14;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList15 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList15);
                            composerStartRestartGroup.updateRememberedValue(arrayList15);
                            obj = arrayList15;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i215 = i6 >> 6;
                        Arrangement.Vertical vertical10 = top;
                        Composer composer10 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical10, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer11, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer11, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i216, FlowLineInfo flowLineInfo, Composer composer11, int i217) {
                                ComposerKt.sourceInformation(composer11, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(302435318, i217, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                                }
                                function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i216), composer11, Integer.valueOf((i217 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i215 & 7168) | (i215 & 14) | 12582912 | (i215 & 112) | (i215 & 896) | ((i6 << 15) & 458752)), composer10, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical2 = vertical10;
                        i22 = i20;
                        composer2 = composer10;
                    } else {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i9 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList16 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList16);
                            composerStartRestartGroup.updateRememberedValue(arrayList16);
                            obj = arrayList16;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList17 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList17);
                            composerStartRestartGroup.updateRememberedValue(arrayList17);
                            obj = arrayList17;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i216 = i6 >> 6;
                        Arrangement.Vertical vertical11 = top;
                        Composer composer11 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical11, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer12, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer12, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i217, FlowLineInfo flowLineInfo, Composer composer12, int i218) {
                                ComposerKt.sourceInformation(composer12, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(302435318, i218, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                                }
                                function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i217), composer12, Integer.valueOf((i218 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i216 & 7168) | (i216 & 14) | 12582912 | (i216 & 112) | (i216 & 896) | ((i6 << 15) & 458752)), composer11, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical2 = vertical11;
                        i22 = i20;
                        composer2 = composer11;
                    }
                    horizontal2 = start;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier7 = modifier2;
                        final int i217 = i19;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer12, Integer num) {
                                invoke(composer12, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer12, int i218) {
                                ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier7, vertical2, horizontal2, i217, i22, contextualFlowColumnOverflow2, function4, composer12, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                            }
                        });
                    }
                }
                i6 |= 24576;
                i12 = i2;
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList18 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList18);
                        composerStartRestartGroup.updateRememberedValue(arrayList18);
                        obj = arrayList18;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList19 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList19);
                        composerStartRestartGroup.updateRememberedValue(arrayList19);
                        obj = arrayList19;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i218 = i6 >> 6;
                    Arrangement.Vertical vertical12 = top;
                    Composer composer12 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical12, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer13, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer13, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i219, FlowLineInfo flowLineInfo, Composer composer13, int i2110) {
                            ComposerKt.sourceInformation(composer13, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i2110, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i219), composer13, Integer.valueOf((i2110 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i218 & 7168) | (i218 & 14) | 12582912 | (i218 & 112) | (i218 & 896) | ((i6 << 15) & 458752)), composer12, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical12;
                    i22 = i20;
                    composer2 = composer12;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList110 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList110);
                        composerStartRestartGroup.updateRememberedValue(arrayList110);
                        obj = arrayList110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList111 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111);
                        composerStartRestartGroup.updateRememberedValue(arrayList111);
                        obj = arrayList111;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i219 = i6 >> 6;
                    Arrangement.Vertical vertical13 = top;
                    Composer composer13 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical13, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer14, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer14, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i2110, FlowLineInfo flowLineInfo, Composer composer14, int i2111) {
                            ComposerKt.sourceInformation(composer14, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i2111, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i2110), composer14, Integer.valueOf((i2111 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i219 & 7168) | (i219 & 14) | 12582912 | (i219 & 112) | (i219 & 896) | ((i6 << 15) & 458752)), composer13, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical13;
                    i22 = i20;
                    composer2 = composer13;
                }
                horizontal2 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier8 = modifier2;
                    final int i2110 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer14, Integer num) {
                            invoke(composer14, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer14, int i2111) {
                            ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier8, vertical2, horizontal2, i2110, i22, contextualFlowColumnOverflow2, function4, composer14, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= 3072;
            start = horizontal;
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i6 |= i13;
                }
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList112 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList112);
                        composerStartRestartGroup.updateRememberedValue(arrayList112);
                        obj = arrayList112;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList113 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList113);
                        composerStartRestartGroup.updateRememberedValue(arrayList113);
                        obj = arrayList113;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2111 = i6 >> 6;
                    Arrangement.Vertical vertical14 = top;
                    Composer composer14 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical14, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer15, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer15, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i2112, FlowLineInfo flowLineInfo, Composer composer15, int i2113) {
                            ComposerKt.sourceInformation(composer15, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i2113, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i2112), composer15, Integer.valueOf((i2113 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2111 & 7168) | (i2111 & 14) | 12582912 | (i2111 & 112) | (i2111 & 896) | ((i6 << 15) & 458752)), composer14, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical14;
                    i22 = i20;
                    composer2 = composer14;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList114 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList114);
                        composerStartRestartGroup.updateRememberedValue(arrayList114);
                        obj = arrayList114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList115 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList115);
                        composerStartRestartGroup.updateRememberedValue(arrayList115);
                        obj = arrayList115;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2112 = i6 >> 6;
                    Arrangement.Vertical vertical15 = top;
                    Composer composer15 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical15, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer16, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer16, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i2113, FlowLineInfo flowLineInfo, Composer composer16, int i2114) {
                            ComposerKt.sourceInformation(composer16, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i2114, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i2113), composer16, Integer.valueOf((i2114 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2112 & 7168) | (i2112 & 14) | 12582912 | (i2112 & 112) | (i2112 & 896) | ((i6 << 15) & 458752)), composer15, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical15;
                    i22 = i20;
                    composer2 = composer15;
                }
                horizontal2 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier9 = modifier2;
                    final int i2113 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer16, Integer num) {
                            invoke(composer16, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer16, int i2114) {
                            ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier9, vertical2, horizontal2, i2113, i22, contextualFlowColumnOverflow2, function4, composer16, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= 24576;
            i12 = i2;
            i14 = i5 & 32;
            if (i14 != 0) {
                i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i6 |= i15;
            }
            i16 = i5 & 64;
            if (i16 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                    i17 = 1048576;
                } else {
                    i17 = 524288;
                }
                i6 |= i17;
            }
            if ((i5 & 128) != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((4793491 & i6) == 4793490) {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i9 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList116 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList116);
                    composerStartRestartGroup.updateRememberedValue(arrayList116);
                    obj = arrayList116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList117 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList117);
                    composerStartRestartGroup.updateRememberedValue(arrayList117);
                    obj = arrayList117;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2114 = i6 >> 6;
                Arrangement.Vertical vertical16 = top;
                Composer composer16 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical16, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer17, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer17, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i2115, FlowLineInfo flowLineInfo, Composer composer17, int i2116) {
                        ComposerKt.sourceInformation(composer17, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(302435318, i2116, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                        }
                        function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i2115), composer17, Integer.valueOf((i2116 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2114 & 7168) | (i2114 & 14) | 12582912 | (i2114 & 112) | (i2114 & 896) | ((i6 << 15) & 458752)), composer16, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical2 = vertical16;
                i22 = i20;
                composer2 = composer16;
            } else {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i9 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList118 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList118);
                    composerStartRestartGroup.updateRememberedValue(arrayList118);
                    obj = arrayList118;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList119 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList119);
                    composerStartRestartGroup.updateRememberedValue(arrayList119);
                    obj = arrayList119;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2115 = i6 >> 6;
                Arrangement.Vertical vertical17 = top;
                Composer composer17 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical17, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer18, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer18, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i2116, FlowLineInfo flowLineInfo, Composer composer18, int i2117) {
                        ComposerKt.sourceInformation(composer18, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(302435318, i2117, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                        }
                        function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i2116), composer18, Integer.valueOf((i2117 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2115 & 7168) | (i2115 & 14) | 12582912 | (i2115 & 112) | (i2115 & 896) | ((i6 << 15) & 458752)), composer17, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical2 = vertical17;
                i22 = i20;
                composer2 = composer17;
            }
            horizontal2 = start;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier10 = modifier2;
                final int i2116 = i19;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer18, Integer num) {
                        invoke(composer18, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer18, int i2117) {
                        ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier10, vertical2, horizontal2, i2116, i22, contextualFlowColumnOverflow2, function4, composer18, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                    }
                });
            }
        }
        i6 |= 48;
        modifier2 = modifier;
        i7 = i5 & 4;
        if (i7 != 0) {
            if ((i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                top = vertical;
                if (composerStartRestartGroup.changed(top)) {
                    i8 = 256;
                } else {
                    i8 = 128;
                }
                i6 |= i8;
            }
            i9 = i5 & 8;
            if (i9 != 0) {
                if ((i4 & 3072) == 0) {
                    start = horizontal;
                    if (composerStartRestartGroup.changed(start)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i6 |= i10;
                }
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i6 |= i13;
                    }
                    i14 = i5 & 32;
                    if (i14 != 0) {
                        i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i6 |= i15;
                    }
                    i16 = i5 & 64;
                    if (i16 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i6 |= i17;
                    }
                    if ((i5 & 128) != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((4793491 & i6) == 4793490) {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i9 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList1110 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1110);
                            composerStartRestartGroup.updateRememberedValue(arrayList1110);
                            obj = arrayList1110;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList1111 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111);
                            composerStartRestartGroup.updateRememberedValue(arrayList1111);
                            obj = arrayList1111;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2117 = i6 >> 6;
                        Arrangement.Vertical vertical18 = top;
                        Composer composer18 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical18, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer19, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer19, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i2118, FlowLineInfo flowLineInfo, Composer composer19, int i2119) {
                                ComposerKt.sourceInformation(composer19, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(302435318, i2119, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                                }
                                function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i2118), composer19, Integer.valueOf((i2119 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2117 & 7168) | (i2117 & 14) | 12582912 | (i2117 & 112) | (i2117 & 896) | ((i6 << 15) & 458752)), composer18, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical2 = vertical18;
                        i22 = i20;
                        composer2 = composer18;
                    } else {
                        if (i23 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i9 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i11 != 0) {
                            i19 = Integer.MAX_VALUE;
                        } else {
                            i19 = i12;
                        }
                        if (i14 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i3;
                        }
                        if (i16 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        i21 = 3670016 & i6;
                        if (i21 == 1048576) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                        if (i21 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            obj = objRememberedValue2;
                            ArrayList arrayList1112 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1112);
                            composerStartRestartGroup.updateRememberedValue(arrayList1112);
                            obj = arrayList1112;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList1113 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1113);
                            composerStartRestartGroup.updateRememberedValue(arrayList1113);
                            obj = arrayList1113;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2118 = i6 >> 6;
                        Arrangement.Vertical vertical19 = top;
                        Composer composer19 = composerStartRestartGroup;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical19, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer110, Integer num2) {
                                invoke(num.intValue(), flowLineInfo, composer110, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i2119, FlowLineInfo flowLineInfo, Composer composer110, int i21110) {
                                ComposerKt.sourceInformation(composer110, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(302435318, i21110, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                                }
                                function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i2119), composer110, Integer.valueOf((i21110 << 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2118 & 7168) | (i2118 & 14) | 12582912 | (i2118 & 112) | (i2118 & 896) | ((i6 << 15) & 458752)), composer19, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical2 = vertical19;
                        i22 = i20;
                        composer2 = composer19;
                    }
                    horizontal2 = start;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier11 = modifier2;
                        final int i2119 = i19;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer110, Integer num) {
                                invoke(composer110, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer110, int i21110) {
                                ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier11, vertical2, horizontal2, i2119, i22, contextualFlowColumnOverflow2, function4, composer110, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                            }
                        });
                    }
                }
                i6 |= 24576;
                i12 = i2;
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1114 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1114);
                        composerStartRestartGroup.updateRememberedValue(arrayList1114);
                        obj = arrayList1114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1115 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1115);
                        composerStartRestartGroup.updateRememberedValue(arrayList1115);
                        obj = arrayList1115;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21110 = i6 >> 6;
                    Arrangement.Vertical vertical110 = top;
                    Composer composer110 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical110, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer111, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer111, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i21111, FlowLineInfo flowLineInfo, Composer composer111, int i21112) {
                            ComposerKt.sourceInformation(composer111, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i21112, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i21111), composer111, Integer.valueOf((i21112 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21110 & 7168) | (i21110 & 14) | 12582912 | (i21110 & 112) | (i21110 & 896) | ((i6 << 15) & 458752)), composer110, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical110;
                    i22 = i20;
                    composer2 = composer110;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1116 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1116);
                        composerStartRestartGroup.updateRememberedValue(arrayList1116);
                        obj = arrayList1116;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1117 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1117);
                        composerStartRestartGroup.updateRememberedValue(arrayList1117);
                        obj = arrayList1117;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21111 = i6 >> 6;
                    Arrangement.Vertical vertical111 = top;
                    Composer composer111 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical111, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer112, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer112, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i21112, FlowLineInfo flowLineInfo, Composer composer112, int i21113) {
                            ComposerKt.sourceInformation(composer112, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i21113, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i21112), composer112, Integer.valueOf((i21113 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21111 & 7168) | (i21111 & 14) | 12582912 | (i21111 & 112) | (i21111 & 896) | ((i6 << 15) & 458752)), composer111, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical111;
                    i22 = i20;
                    composer2 = composer111;
                }
                horizontal2 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier12 = modifier2;
                    final int i21112 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer112, Integer num) {
                            invoke(composer112, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer112, int i21113) {
                            ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier12, vertical2, horizontal2, i21112, i22, contextualFlowColumnOverflow2, function4, composer112, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= 3072;
            start = horizontal;
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i6 |= i13;
                }
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1118 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1118);
                        composerStartRestartGroup.updateRememberedValue(arrayList1118);
                        obj = arrayList1118;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1119 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1119);
                        composerStartRestartGroup.updateRememberedValue(arrayList1119);
                        obj = arrayList1119;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21113 = i6 >> 6;
                    Arrangement.Vertical vertical112 = top;
                    Composer composer112 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical112, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer113, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer113, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i21114, FlowLineInfo flowLineInfo, Composer composer113, int i21115) {
                            ComposerKt.sourceInformation(composer113, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i21115, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i21114), composer113, Integer.valueOf((i21115 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21113 & 7168) | (i21113 & 14) | 12582912 | (i21113 & 112) | (i21113 & 896) | ((i6 << 15) & 458752)), composer112, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical112;
                    i22 = i20;
                    composer2 = composer112;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11110 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11110);
                        composerStartRestartGroup.updateRememberedValue(arrayList11110);
                        obj = arrayList11110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11111 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11111);
                        composerStartRestartGroup.updateRememberedValue(arrayList11111);
                        obj = arrayList11111;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21114 = i6 >> 6;
                    Arrangement.Vertical vertical113 = top;
                    Composer composer113 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical113, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer114, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer114, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i21115, FlowLineInfo flowLineInfo, Composer composer114, int i21116) {
                            ComposerKt.sourceInformation(composer114, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i21116, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i21115), composer114, Integer.valueOf((i21116 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21114 & 7168) | (i21114 & 14) | 12582912 | (i21114 & 112) | (i21114 & 896) | ((i6 << 15) & 458752)), composer113, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical113;
                    i22 = i20;
                    composer2 = composer113;
                }
                horizontal2 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier13 = modifier2;
                    final int i21115 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer114, Integer num) {
                            invoke(composer114, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer114, int i21116) {
                            ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier13, vertical2, horizontal2, i21115, i22, contextualFlowColumnOverflow2, function4, composer114, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= 24576;
            i12 = i2;
            i14 = i5 & 32;
            if (i14 != 0) {
                i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i6 |= i15;
            }
            i16 = i5 & 64;
            if (i16 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                    i17 = 1048576;
                } else {
                    i17 = 524288;
                }
                i6 |= i17;
            }
            if ((i5 & 128) != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((4793491 & i6) == 4793490) {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i9 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList11112 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11112);
                    composerStartRestartGroup.updateRememberedValue(arrayList11112);
                    obj = arrayList11112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList11113 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11113);
                    composerStartRestartGroup.updateRememberedValue(arrayList11113);
                    obj = arrayList11113;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i21116 = i6 >> 6;
                Arrangement.Vertical vertical114 = top;
                Composer composer114 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical114, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer115, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer115, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i21117, FlowLineInfo flowLineInfo, Composer composer115, int i21118) {
                        ComposerKt.sourceInformation(composer115, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(302435318, i21118, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                        }
                        function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i21117), composer115, Integer.valueOf((i21118 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21116 & 7168) | (i21116 & 14) | 12582912 | (i21116 & 112) | (i21116 & 896) | ((i6 << 15) & 458752)), composer114, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical2 = vertical114;
                i22 = i20;
                composer2 = composer114;
            } else {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i9 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList11114 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11114);
                    composerStartRestartGroup.updateRememberedValue(arrayList11114);
                    obj = arrayList11114;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList11115 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11115);
                    composerStartRestartGroup.updateRememberedValue(arrayList11115);
                    obj = arrayList11115;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i21117 = i6 >> 6;
                Arrangement.Vertical vertical115 = top;
                Composer composer115 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical115, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer116, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer116, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i21118, FlowLineInfo flowLineInfo, Composer composer116, int i21119) {
                        ComposerKt.sourceInformation(composer116, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(302435318, i21119, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                        }
                        function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i21118), composer116, Integer.valueOf((i21119 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21117 & 7168) | (i21117 & 14) | 12582912 | (i21117 & 112) | (i21117 & 896) | ((i6 << 15) & 458752)), composer115, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical2 = vertical115;
                i22 = i20;
                composer2 = composer115;
            }
            horizontal2 = start;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier14 = modifier2;
                final int i21118 = i19;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer116, Integer num) {
                        invoke(composer116, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer116, int i21119) {
                        ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier14, vertical2, horizontal2, i21118, i22, contextualFlowColumnOverflow2, function4, composer116, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                    }
                });
            }
        }
        i6 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        top = vertical;
        i9 = i5 & 8;
        if (i9 != 0) {
            if ((i4 & 3072) == 0) {
                start = horizontal;
                if (composerStartRestartGroup.changed(start)) {
                    i10 = 2048;
                } else {
                    i10 = 1024;
                }
                i6 |= i10;
            }
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i6 |= i13;
                }
                i14 = i5 & 32;
                if (i14 != 0) {
                    i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i6 |= i15;
                }
                i16 = i5 & 64;
                if (i16 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i6 |= i17;
                }
                if ((i5 & 128) != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((4793491 & i6) == 4793490) {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11116 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11116);
                        composerStartRestartGroup.updateRememberedValue(arrayList11116);
                        obj = arrayList11116;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11117 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11117);
                        composerStartRestartGroup.updateRememberedValue(arrayList11117);
                        obj = arrayList11117;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21119 = i6 >> 6;
                    Arrangement.Vertical vertical116 = top;
                    Composer composer116 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical116, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer117, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer117, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i211110, FlowLineInfo flowLineInfo, Composer composer117, int i211111) {
                            ComposerKt.sourceInformation(composer117, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i211111, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i211110), composer117, Integer.valueOf((i211111 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i21119 & 7168) | (i21119 & 14) | 12582912 | (i21119 & 112) | (i21119 & 896) | ((i6 << 15) & 458752)), composer116, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical116;
                    i22 = i20;
                    composer2 = composer116;
                } else {
                    if (i23 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i9 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i11 != 0) {
                        i19 = Integer.MAX_VALUE;
                    } else {
                        i19 = i12;
                    }
                    if (i14 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i3;
                    }
                    if (i16 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    i21 = 3670016 & i6;
                    if (i21 == 1048576) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                    if (i21 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11118 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11118);
                        composerStartRestartGroup.updateRememberedValue(arrayList11118);
                        obj = arrayList11118;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11119 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11119);
                        composerStartRestartGroup.updateRememberedValue(arrayList11119);
                        obj = arrayList11119;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211110 = i6 >> 6;
                    Arrangement.Vertical vertical117 = top;
                    Composer composer117 = composerStartRestartGroup;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical117, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer118, Integer num2) {
                            invoke(num.intValue(), flowLineInfo, composer118, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i211111, FlowLineInfo flowLineInfo, Composer composer118, int i211112) {
                            ComposerKt.sourceInformation(composer118, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(302435318, i211112, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                            }
                            function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i211111), composer118, Integer.valueOf((i211112 << 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211110 & 7168) | (i211110 & 14) | 12582912 | (i211110 & 112) | (i211110 & 896) | ((i6 << 15) & 458752)), composer117, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical2 = vertical117;
                    i22 = i20;
                    composer2 = composer117;
                }
                horizontal2 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier15 = modifier2;
                    final int i211111 = i19;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer118, Integer num) {
                            invoke(composer118, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer118, int i211112) {
                            ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier15, vertical2, horizontal2, i211111, i22, contextualFlowColumnOverflow2, function4, composer118, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                        }
                    });
                }
            }
            i6 |= 24576;
            i12 = i2;
            i14 = i5 & 32;
            if (i14 != 0) {
                i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i6 |= i15;
            }
            i16 = i5 & 64;
            if (i16 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                    i17 = 1048576;
                } else {
                    i17 = 524288;
                }
                i6 |= i17;
            }
            if ((i5 & 128) != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((4793491 & i6) == 4793490) {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i9 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111110 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111110);
                    composerStartRestartGroup.updateRememberedValue(arrayList111110);
                    obj = arrayList111110;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111111 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111111);
                    composerStartRestartGroup.updateRememberedValue(arrayList111111);
                    obj = arrayList111111;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i211112 = i6 >> 6;
                Arrangement.Vertical vertical118 = top;
                Composer composer118 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical118, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer119, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer119, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i211113, FlowLineInfo flowLineInfo, Composer composer119, int i211114) {
                        ComposerKt.sourceInformation(composer119, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(302435318, i211114, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                        }
                        function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i211113), composer119, Integer.valueOf((i211114 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211112 & 7168) | (i211112 & 14) | 12582912 | (i211112 & 112) | (i211112 & 896) | ((i6 << 15) & 458752)), composer118, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical2 = vertical118;
                i22 = i20;
                composer2 = composer118;
            } else {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i9 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111112 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111112);
                    composerStartRestartGroup.updateRememberedValue(arrayList111112);
                    obj = arrayList111112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111113 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111113);
                    composerStartRestartGroup.updateRememberedValue(arrayList111113);
                    obj = arrayList111113;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i211113 = i6 >> 6;
                Arrangement.Vertical vertical119 = top;
                Composer composer119 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical119, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer1110, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer1110, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i211114, FlowLineInfo flowLineInfo, Composer composer1110, int i211115) {
                        ComposerKt.sourceInformation(composer1110, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(302435318, i211115, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                        }
                        function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i211114), composer1110, Integer.valueOf((i211115 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211113 & 7168) | (i211113 & 14) | 12582912 | (i211113 & 112) | (i211113 & 896) | ((i6 << 15) & 458752)), composer119, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical2 = vertical119;
                i22 = i20;
                composer2 = composer119;
            }
            horizontal2 = start;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier16 = modifier2;
                final int i211114 = i19;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer1110, Integer num) {
                        invoke(composer1110, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer1110, int i211115) {
                        ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier16, vertical2, horizontal2, i211114, i22, contextualFlowColumnOverflow2, function4, composer1110, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                    }
                });
            }
        }
        i6 |= 3072;
        start = horizontal;
        i11 = i5 & 16;
        if (i11 != 0) {
            if ((i4 & 24576) == 0) {
                i12 = i2;
                if (composerStartRestartGroup.changed(i12)) {
                    i13 = 16384;
                } else {
                    i13 = 8192;
                }
                i6 |= i13;
            }
            i14 = i5 & 32;
            if (i14 != 0) {
                i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i6 |= i15;
            }
            i16 = i5 & 64;
            if (i16 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                    i17 = 1048576;
                } else {
                    i17 = 524288;
                }
                i6 |= i17;
            }
            if ((i5 & 128) != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((4793491 & i6) == 4793490) {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i9 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111114 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111114);
                    composerStartRestartGroup.updateRememberedValue(arrayList111114);
                    obj = arrayList111114;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111115 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111115);
                    composerStartRestartGroup.updateRememberedValue(arrayList111115);
                    obj = arrayList111115;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i211115 = i6 >> 6;
                Arrangement.Vertical vertical1110 = top;
                Composer composer1110 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical1110, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer1111, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer1111, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i211116, FlowLineInfo flowLineInfo, Composer composer1111, int i211117) {
                        ComposerKt.sourceInformation(composer1111, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(302435318, i211117, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                        }
                        function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i211116), composer1111, Integer.valueOf((i211117 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211115 & 7168) | (i211115 & 14) | 12582912 | (i211115 & 112) | (i211115 & 896) | ((i6 << 15) & 458752)), composer1110, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical2 = vertical1110;
                i22 = i20;
                composer2 = composer1110;
            } else {
                if (i23 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i9 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i11 != 0) {
                    i19 = Integer.MAX_VALUE;
                } else {
                    i19 = i12;
                }
                if (i14 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i3;
                }
                if (i16 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                i21 = 3670016 & i6;
                if (i21 == 1048576) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
                if (i21 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111116 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111116);
                    composerStartRestartGroup.updateRememberedValue(arrayList111116);
                    obj = arrayList111116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111117 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111117);
                    composerStartRestartGroup.updateRememberedValue(arrayList111117);
                    obj = arrayList111117;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i211116 = i6 >> 6;
                Arrangement.Vertical vertical1111 = top;
                Composer composer1111 = composerStartRestartGroup;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical1111, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer1112, Integer num2) {
                        invoke(num.intValue(), flowLineInfo, composer1112, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i211117, FlowLineInfo flowLineInfo, Composer composer1112, int i211118) {
                        ComposerKt.sourceInformation(composer1112, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(302435318, i211118, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                        }
                        function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i211117), composer1112, Integer.valueOf((i211118 << 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211116 & 7168) | (i211116 & 14) | 12582912 | (i211116 & 112) | (i211116 & 896) | ((i6 << 15) & 458752)), composer1111, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical2 = vertical1111;
                i22 = i20;
                composer2 = composer1111;
            }
            horizontal2 = start;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier17 = modifier2;
                final int i211117 = i19;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer1112, Integer num) {
                        invoke(composer1112, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer1112, int i211118) {
                        ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier17, vertical2, horizontal2, i211117, i22, contextualFlowColumnOverflow2, function4, composer1112, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                    }
                });
            }
        }
        i6 |= 24576;
        i12 = i2;
        i14 = i5 & 32;
        if (i14 != 0) {
            i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changed(i3)) {
                i15 = 131072;
            } else {
                i15 = 65536;
            }
            i6 |= i15;
        }
        i16 = i5 & 64;
        if (i16 != 0) {
            i6 |= 1572864;
        } else if ((i4 & 1572864) == 0) {
            if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                i17 = 1048576;
            } else {
                i17 = 524288;
            }
            i6 |= i17;
        }
        if ((i5 & 128) != 0) {
            i6 |= 12582912;
        } else if ((i4 & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function4)) {
                i18 = 8388608;
            } else {
                i18 = 4194304;
            }
            i6 |= i18;
        }
        if ((4793491 & i6) == 4793490) {
            if (i23 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i7 != 0) {
                top = Arrangement.INSTANCE.getTop();
            }
            if (i9 != 0) {
                start = Arrangement.INSTANCE.getStart();
            }
            if (i11 != 0) {
                i19 = Integer.MAX_VALUE;
            } else {
                i19 = i12;
            }
            if (i14 != 0) {
                i20 = Integer.MAX_VALUE;
            } else {
                i20 = i3;
            }
            if (i16 != 0) {
                clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
            } else {
                clip = contextualFlowColumnOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
            i21 = 3670016 & i6;
            if (i21 == 1048576) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
            if (i21 == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z2) {
                obj = objRememberedValue2;
                ArrayList arrayList111118 = new ArrayList();
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111118);
                composerStartRestartGroup.updateRememberedValue(arrayList111118);
                obj = arrayList111118;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList111119 = new ArrayList();
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111119);
                composerStartRestartGroup.updateRememberedValue(arrayList111119);
                obj = arrayList111119;
            }
            obj = objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i211118 = i6 >> 6;
            Arrangement.Vertical vertical1112 = top;
            Composer composer1112 = composerStartRestartGroup;
            SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical1112, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer1113, Integer num2) {
                    invoke(num.intValue(), flowLineInfo, composer1113, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i211119, FlowLineInfo flowLineInfo, Composer composer1113, int i2111110) {
                    ComposerKt.sourceInformation(composer1113, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(302435318, i2111110, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                    }
                    function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i211119), composer1113, Integer.valueOf((i2111110 << 3) & 112));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211118 & 7168) | (i211118 & 14) | 12582912 | (i211118 & 112) | (i211118 & 896) | ((i6 << 15) & 458752)), composer1112, (i6 >> 3) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contextualFlowColumnOverflow2 = clip;
            vertical2 = vertical1112;
            i22 = i20;
            composer2 = composer1112;
        } else {
            if (i23 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i7 != 0) {
                top = Arrangement.INSTANCE.getTop();
            }
            if (i9 != 0) {
                start = Arrangement.INSTANCE.getStart();
            }
            if (i11 != 0) {
                i19 = Integer.MAX_VALUE;
            } else {
                i19 = i12;
            }
            if (i14 != 0) {
                i20 = Integer.MAX_VALUE;
            } else {
                i20 = i3;
            }
            if (i16 != 0) {
                clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
            } else {
                clip = contextualFlowColumnOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-588476895, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:148)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055927072, "CC(remember):ContextualFlowLayout.kt#9igjgp");
            i21 = 3670016 & i6;
            if (i21 == 1048576) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1055923392, "CC(remember):ContextualFlowLayout.kt#9igjgp");
            if (i21 == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z2) {
                obj = objRememberedValue2;
                ArrayList arrayList1111110 = new ArrayList();
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111110);
                composerStartRestartGroup.updateRememberedValue(arrayList1111110);
                obj = arrayList1111110;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList1111111 = new ArrayList();
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111111);
                composerStartRestartGroup.updateRememberedValue(arrayList1111111);
                obj = arrayList1111111;
            }
            obj = objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i211119 = i6 >> 6;
            Arrangement.Vertical vertical1113 = top;
            Composer composer1113 = composerStartRestartGroup;
            SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(vertical1113, start, i19, i20, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(302435318, true, new Function4<Integer, FlowLineInfo, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt$ContextualFlowColumn$measurePolicy$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, FlowLineInfo flowLineInfo, Composer composer1114, Integer num2) {
                    invoke(num.intValue(), flowLineInfo, composer1114, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i2111110, FlowLineInfo flowLineInfo, Composer composer1114, int i2111111) {
                    ComposerKt.sourceInformation(composer1114, "C172@7451L14:ContextualFlowLayout.kt#2w3rfo");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(302435318, i2111111, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:166)");
                    }
                    function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i2111110), composer1114, Integer.valueOf((i2111111 << 3) & 112));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211119 & 7168) | (i211119 & 14) | 12582912 | (i211119 & 112) | (i211119 & 896) | ((i6 << 15) & 458752)), composer1113, (i6 >> 3) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contextualFlowColumnOverflow2 = clip;
            vertical2 = vertical1113;
            i22 = i20;
            composer2 = composer1113;
        }
        horizontal2 = start;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier18 = modifier2;
            final int i2111110 = i19;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer1114, Integer num) {
                    invoke(composer1114, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer1114, int i2111111) {
                    ContextualFlowLayoutKt.ContextualFlowColumn(i, modifier18, vertical2, horizontal2, i2111110, i22, contextualFlowColumnOverflow2, function4, composer1114, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00b9  */
    public static final Function2<SubcomposeMeasureScope, Constraints, MeasureResult> contextualRowMeasurementHelper(Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, int i3, List<? extends Function2<? super Composer, ? super Integer, Unit>> list, Function4<? super Integer, ? super FlowLineInfo, ? super Composer, ? super Integer, Unit> function4, Composer composer, int i4) {
        boolean z;
        ComposerKt.sourceInformationMarkerStart(composer, -43351224, "C(contextualRowMeasurementHelper)P(1,7,3,4,6,2,5)343@13891L847:ContextualFlowLayout.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-43351224, i4, -1, "androidx.compose.foundation.layout.contextualRowMeasurementHelper (ContextualFlowLayout.kt:342)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 1138967354, "CC(remember):ContextualFlowLayout.kt#9igjgp");
        boolean zChanged = ((((i4 & 14) ^ 6) > 4 && composer.changed(horizontal)) || (i4 & 6) == 4) | ((((i4 & 112) ^ 48) > 32 && composer.changed(vertical)) || (i4 & 48) == 32) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(i)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | ((((i4 & 7168) ^ 3072) > 2048 && composer.changed(i2)) || (i4 & 3072) == 2048) | composer.changed(flowLayoutOverflowState) | ((((458752 & i4) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072 && composer.changed(i3)) || (i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072);
        if (((29360128 & i4) ^ 12582912) <= 8388608 || !composer.changed(function4)) {
            z = (i4 & 12582912) == 8388608;
        }
        boolean z2 = zChanged | z;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new FlowMeasureLazyPolicy(true, horizontal, vertical, horizontal.getSpacing(), FlowLayoutKt.getCROSS_AXIS_ALIGNMENT_TOP(), vertical.getSpacing(), i3, i2, i, flowLayoutOverflowState, list, function4, null).getMeasurePolicy();
            composer.updateRememberedValue(objRememberedValue);
        }
        Function2<SubcomposeMeasureScope, Constraints, MeasureResult> function2 = (Function2) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return function2;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00b9  */
    public static final Function2<SubcomposeMeasureScope, Constraints, MeasureResult> contextualColumnMeasureHelper(Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, int i3, List<? extends Function2<? super Composer, ? super Integer, Unit>> list, Function4<? super Integer, ? super FlowLineInfo, ? super Composer, ? super Integer, Unit> function4, Composer composer, int i4) {
        boolean z;
        ComposerKt.sourceInformationMarkerStart(composer, 1009762916, "C(contextualColumnMeasureHelper)P(7,1,3,4,6,2,5)383@15228L850:ContextualFlowLayout.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1009762916, i4, -1, "androidx.compose.foundation.layout.contextualColumnMeasureHelper (ContextualFlowLayout.kt:382)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1978242771, "CC(remember):ContextualFlowLayout.kt#9igjgp");
        boolean zChanged = ((((i4 & 14) ^ 6) > 4 && composer.changed(vertical)) || (i4 & 6) == 4) | ((((i4 & 112) ^ 48) > 32 && composer.changed(horizontal)) || (i4 & 48) == 32) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(i)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | ((((i4 & 7168) ^ 3072) > 2048 && composer.changed(i2)) || (i4 & 3072) == 2048) | composer.changed(flowLayoutOverflowState) | ((((458752 & i4) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072 && composer.changed(i3)) || (i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072);
        if (((29360128 & i4) ^ 12582912) <= 8388608 || !composer.changed(function4)) {
            z = (i4 & 12582912) == 8388608;
        }
        boolean z2 = zChanged | z;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new FlowMeasureLazyPolicy(false, horizontal, vertical, vertical.getSpacing(), FlowLayoutKt.getCROSS_AXIS_ALIGNMENT_START(), horizontal.getSpacing(), i3, i2, i, flowLayoutOverflowState, list, function4, null).getMeasurePolicy();
            composer.updateRememberedValue(objRememberedValue);
        }
        Function2<SubcomposeMeasureScope, Constraints, MeasureResult> function2 = (Function2) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return function2;
    }
}
