package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.ScrollingContainerKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsState;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGrid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0089\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001a"}, d2 = {"LazyStaggeredGrid", "", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "slots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "modifier", "Landroidx/compose/ui/Modifier;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacing", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;", "Lkotlin/ExtensionFunctionType;", "LazyStaggeredGrid-LJWHXA8", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/FlingBehavior;ZFFLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridKt {
    /* JADX WARN: Code duplicated, block: B:102:0x0124  */
    /* JADX WARN: Code duplicated, block: B:103:0x0127  */
    /* JADX WARN: Code duplicated, block: B:105:0x012b  */
    /* JADX WARN: Code duplicated, block: B:107:0x0135  */
    /* JADX WARN: Code duplicated, block: B:108:0x0138  */
    /* JADX WARN: Code duplicated, block: B:113:0x0145  */
    /* JADX WARN: Code duplicated, block: B:114:0x0148  */
    /* JADX WARN: Code duplicated, block: B:116:0x014c  */
    /* JADX WARN: Code duplicated, block: B:118:0x0152  */
    /* JADX WARN: Code duplicated, block: B:119:0x0154  */
    /* JADX WARN: Code duplicated, block: B:121:0x0158  */
    /* JADX WARN: Code duplicated, block: B:124:0x0166  */
    /* JADX WARN: Code duplicated, block: B:130:0x0184  */
    /* JADX WARN: Code duplicated, block: B:132:0x018c  */
    /* JADX WARN: Code duplicated, block: B:140:0x01b6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:141:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:142:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:145:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:146:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:148:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:151:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:152:0x01de  */
    /* JADX WARN: Code duplicated, block: B:154:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:155:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:157:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:158:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:160:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:161:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:165:0x020e  */
    /* JADX WARN: Code duplicated, block: B:168:0x023d  */
    /* JADX WARN: Code duplicated, block: B:171:0x037b  */
    /* JADX WARN: Code duplicated, block: B:175:0x038f  */
    /* JADX WARN: Code duplicated, block: B:177:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004f  */
    /* JADX WARN: Code duplicated, block: B:27:0x0052  */
    /* JADX WARN: Code duplicated, block: B:29:0x0056  */
    /* JADX WARN: Code duplicated, block: B:31:0x005a  */
    /* JADX WARN: Code duplicated, block: B:32:0x005f  */
    /* JADX WARN: Code duplicated, block: B:34:0x0065  */
    /* JADX WARN: Code duplicated, block: B:35:0x0068  */
    /* JADX WARN: Code duplicated, block: B:39:0x006f  */
    /* JADX WARN: Code duplicated, block: B:40:0x0072  */
    /* JADX WARN: Code duplicated, block: B:42:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x007e  */
    /* JADX WARN: Code duplicated, block: B:45:0x0081  */
    /* JADX WARN: Code duplicated, block: B:50:0x008b  */
    /* JADX WARN: Code duplicated, block: B:51:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0092  */
    /* JADX WARN: Code duplicated, block: B:55:0x009a  */
    /* JADX WARN: Code duplicated, block: B:56:0x009d  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:67:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00db  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:84:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:91:0x0101  */
    /* JADX WARN: Code duplicated, block: B:92:0x0104  */
    /* JADX WARN: Code duplicated, block: B:94:0x0108  */
    /* JADX WARN: Code duplicated, block: B:96:0x0112  */
    /* JADX WARN: Code duplicated, block: B:97:0x0115  */
    /* JADX INFO: renamed from: LazyStaggeredGrid-LJWHXA8, reason: not valid java name */
    public static final void m928LazyStaggeredGridLJWHXA8(final LazyStaggeredGridState lazyStaggeredGridState, final Orientation orientation, final LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider, Modifier modifier, PaddingValues paddingValues, boolean z, FlingBehavior flingBehavior, boolean z2, float f, float f2, final Function1<? super LazyStaggeredGridScope, Unit> function1, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        boolean zChangedInstance;
        int i5;
        int i6;
        Modifier modifier2;
        int i7;
        int i8;
        PaddingValues paddingValues2;
        int i9;
        int i10;
        boolean z3;
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
        Modifier.Companion companion;
        PaddingValues paddingValuesM713PaddingValues0680j_4;
        FlingBehavior flingBehavior2;
        int i21;
        float fM6890constructorimpl;
        int i22;
        Modifier modifier3;
        PaddingValues paddingValues3;
        Object objRememberedValue;
        final boolean z4;
        Composer composer2;
        final Modifier modifier4;
        final PaddingValues paddingValues4;
        final float f3;
        final float f4;
        final FlingBehavior flingBehavior3;
        final boolean z5;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i23;
        Composer composerStartRestartGroup = composer.startRestartGroup(288295126);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LazyStaggeredGrid)P(9,6,8,5,1,7,3,10,4:c#ui.unit.Dp,2:c#ui.unit.Dp)51@2370L15,61@2769L55,62@2850L24,63@2922L7,64@2954L266,76@3245L60,82@3456L278,90@3804L57,93@4024L7,89@3748L385,98@4194L316,78@3311L1332:LazyStaggeredGrid.kt#fzvcnm");
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(lazyStaggeredGridState) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        if ((i3 & 2) == 0) {
            if ((i & 48) == 0) {
                i4 |= composerStartRestartGroup.changed(orientation) ? 32 : 16;
            }
            if ((i3 & 4) != 0) {
                i4 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            } else if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i & 512) == 0) {
                    zChangedInstance = composerStartRestartGroup.changed(lazyGridStaggeredGridSlotsProvider);
                } else {
                    zChangedInstance = composerStartRestartGroup.changedInstance(lazyGridStaggeredGridSlotsProvider);
                }
                if (zChangedInstance) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i4 |= i5;
            }
            i6 = i3 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i4 |= i7;
                }
                i8 = i3 & 16;
                if (i8 != 0) {
                    if ((i & 24576) == 0) {
                        paddingValues2 = paddingValues;
                        if (composerStartRestartGroup.changed(paddingValues2)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i4 |= i9;
                    }
                    i10 = i3 & 32;
                    if (i10 != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        z3 = z;
                    } else {
                        z3 = z;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(z3)) {
                                i11 = 131072;
                            } else {
                                i11 = 65536;
                            }
                            i4 |= i11;
                        }
                    }
                    if ((i & 1572864) != 0) {
                        if ((i3 & 64) == 0 || !composerStartRestartGroup.changed(flingBehavior)) {
                            i23 = 524288;
                        } else {
                            i23 = 1048576;
                        }
                        i4 |= i23;
                    }
                    i12 = i3 & 128;
                    if (i12 != 0) {
                        i4 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i13 = 8388608;
                        } else {
                            i13 = 4194304;
                        }
                        i4 |= i13;
                    }
                    i14 = i3 & 256;
                    if (i14 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(f)) {
                                i15 = 67108864;
                            } else {
                                i15 = 33554432;
                            }
                            i4 |= i15;
                        }
                        i16 = i3 & 512;
                        if (i16 != 0) {
                            if ((i & BaseAdapter.HEADER) == 0) {
                                if (composerStartRestartGroup.changed(f2)) {
                                    i17 = 536870912;
                                } else {
                                    i17 = 268435456;
                                }
                                i4 |= i17;
                            }
                            if ((i3 & 1024) != 0) {
                                i18 = i2 | 6;
                            } else if ((i2 & 6) == 0) {
                                if (composerStartRestartGroup.changedInstance(function1)) {
                                    i19 = 4;
                                } else {
                                    i19 = 2;
                                }
                                i18 = i2 | i19;
                            } else {
                                i18 = i2;
                            }
                            i20 = i4;
                            if ((i20 & 306783379) == 306783378 || (i18 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i6 != 0) {
                                        companion = Modifier.INSTANCE;
                                    } else {
                                        companion = modifier2;
                                    }
                                    if (i8 != 0) {
                                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                    } else {
                                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                    }
                                    if (i10 != 0) {
                                        z3 = false;
                                    }
                                    if ((i3 & 64) != 0) {
                                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                        i21 = i20 & (-3670017);
                                    } else {
                                        flingBehavior2 = flingBehavior;
                                        i21 = i20;
                                    }
                                    if (i12 != 0) {
                                        z2 = true;
                                    } else {
                                        z2 = z2;
                                    }
                                    if (i14 != 0) {
                                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                    } else {
                                        fM6890constructorimpl = f;
                                    }
                                    if (i16 != 0) {
                                        f2 = Dp.m6890constructorimpl(0.0f);
                                    } else {
                                        f2 = f2;
                                    }
                                    flingBehavior = flingBehavior2;
                                    i22 = i21;
                                    f = fM6890constructorimpl;
                                    modifier3 = companion;
                                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    i22 = (i3 & 64) != 0 ? i20 & (-3670017) : i20;
                                    modifier3 = modifier2;
                                    paddingValues3 = paddingValues2;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                                }
                                int i24 = i22 & 14;
                                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i24);
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                                    objRememberedValue = compositionScopedCoroutineScopeCanceller;
                                }
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext = CompositionLocalsKt.getLocalGraphicsContext();
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                Object objConsume = composerStartRestartGroup.consume(localGraphicsContext);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                int i25 = i22 >> 6;
                                int i26 = (i25 & 896) | i24;
                                int i27 = i25 & 7168;
                                int i28 = i22 >> 9;
                                int i29 = i26 | i27 | ((i22 << 9) & 57344) | (i28 & 458752) | (i28 & 3670016) | ((i22 << 18) & 234881024);
                                boolean z6 = z3;
                                int i30 = i22;
                                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda, paddingValues3, z6, orientation, f, f2, coroutineScope, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume, composerStartRestartGroup, i29);
                                PaddingValues paddingValues5 = paddingValues3;
                                float f5 = f;
                                float f6 = f2;
                                int i31 = i30 >> 12;
                                boolean z7 = z2;
                                Modifier modifierLazyLayoutSemantics = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z6, composerStartRestartGroup, (i31 & 112) | i24), orientation, z7, z6, composerStartRestartGroup, ((i30 << 6) & 7168) | (i28 & 57344) | (i30 & 458752));
                                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i24);
                                LazyLayoutBeyondBoundsInfo beyondBoundsInfo = lazyStaggeredGridState.getBeyondBoundsInfo();
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                int i32 = i30 >> 3;
                                FlingBehavior flingBehavior4 = flingBehavior;
                                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState, beyondBoundsInfo, z6, (LayoutDirection) objConsume2, orientation, z7, composerStartRestartGroup, (3670016 & i32) | (MutableVector.$stable << 6) | i27 | ((i30 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z7, z6, flingBehavior4, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i30 << 3) & 1008) | (i31 & 7168) | (i32 & 57344) | (i32 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE, composerStartRestartGroup, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                z4 = z7;
                                composer2 = composerStartRestartGroup;
                                modifier4 = modifier3;
                                paddingValues4 = paddingValues5;
                                f3 = f5;
                                f4 = f6;
                                flingBehavior3 = flingBehavior4;
                                z5 = z6;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                z4 = z2;
                                f4 = f2;
                                z5 = z3;
                                composer2 = composerStartRestartGroup;
                                modifier4 = modifier2;
                                paddingValues4 = paddingValues2;
                                flingBehavior3 = flingBehavior;
                                f3 = f;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                                    public final void invoke(Composer composer3, int i33) {
                                        LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                                    }
                                });
                            }
                        }
                        i4 |= BaseAdapter.HEADER;
                        if ((i3 & 1024) != 0) {
                            i18 = i2 | 6;
                        } else if ((i2 & 6) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i19 = 4;
                            } else {
                                i19 = 2;
                            }
                            i18 = i2 | i19;
                        } else {
                            i18 = i2;
                        }
                        i20 = i4;
                        if ((i20 & 306783379) == 306783378) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            } else {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                            }
                            int i210 = i22 & 14;
                            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda2 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i210);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller2 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller2);
                                objRememberedValue = compositionScopedCoroutineScopeCanceller2;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CoroutineScope coroutineScope2 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext2 = CompositionLocalsKt.getLocalGraphicsContext();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = composerStartRestartGroup.consume(localGraphicsContext2);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i211 = i22 >> 6;
                            int i212 = (i211 & 896) | i210;
                            int i213 = i211 & 7168;
                            int i214 = i22 >> 9;
                            int i215 = i212 | i213 | ((i22 << 9) & 57344) | (i214 & 458752) | (i214 & 3670016) | ((i22 << 18) & 234881024);
                            boolean z8 = z3;
                            int i33 = i22;
                            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE2 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda2, paddingValues3, z8, orientation, f, f2, coroutineScope2, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume3, composerStartRestartGroup, i215);
                            PaddingValues paddingValues6 = paddingValues3;
                            float f7 = f;
                            float f8 = f2;
                            int i34 = i33 >> 12;
                            boolean z9 = z2;
                            Modifier modifierLazyLayoutSemantics2 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda2, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z8, composerStartRestartGroup, (i34 & 112) | i210), orientation, z9, z8, composerStartRestartGroup, ((i33 << 6) & 7168) | (i214 & 57344) | (i33 & 458752));
                            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState2 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i210);
                            LazyLayoutBeyondBoundsInfo beyondBoundsInfo2 = lazyStaggeredGridState.getBeyondBoundsInfo();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume4 = composerStartRestartGroup.consume(localLayoutDirection2);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i35 = i33 >> 3;
                            FlingBehavior flingBehavior5 = flingBehavior;
                            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda2, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics2, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState2, beyondBoundsInfo2, z8, (LayoutDirection) objConsume4, orientation, z9, composerStartRestartGroup, (3670016 & i35) | (MutableVector.$stable << 6) | i213 | ((i33 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z9, z8, flingBehavior5, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i33 << 3) & 1008) | (i34 & 7168) | (i35 & 57344) | (i35 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE2, composerStartRestartGroup, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z9;
                            composer2 = composerStartRestartGroup;
                            modifier4 = modifier3;
                            paddingValues4 = paddingValues6;
                            f3 = f7;
                            f4 = f8;
                            flingBehavior3 = flingBehavior5;
                            z5 = z8;
                        } else {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            } else {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                            }
                            int i216 = i22 & 14;
                            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda3 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i216);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller3 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller3);
                                objRememberedValue = compositionScopedCoroutineScopeCanceller3;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CoroutineScope coroutineScope3 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext3 = CompositionLocalsKt.getLocalGraphicsContext();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = composerStartRestartGroup.consume(localGraphicsContext3);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i217 = i22 >> 6;
                            int i218 = (i217 & 896) | i216;
                            int i219 = i217 & 7168;
                            int i2110 = i22 >> 9;
                            int i2111 = i218 | i219 | ((i22 << 9) & 57344) | (i2110 & 458752) | (i2110 & 3670016) | ((i22 << 18) & 234881024);
                            boolean z10 = z3;
                            int i36 = i22;
                            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE3 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda3, paddingValues3, z10, orientation, f, f2, coroutineScope3, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume5, composerStartRestartGroup, i2111);
                            PaddingValues paddingValues7 = paddingValues3;
                            float f9 = f;
                            float f10 = f2;
                            int i37 = i36 >> 12;
                            boolean z11 = z2;
                            Modifier modifierLazyLayoutSemantics3 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda3, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z10, composerStartRestartGroup, (i37 & 112) | i216), orientation, z11, z10, composerStartRestartGroup, ((i36 << 6) & 7168) | (i2110 & 57344) | (i36 & 458752));
                            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState3 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i216);
                            LazyLayoutBeyondBoundsInfo beyondBoundsInfo3 = lazyStaggeredGridState.getBeyondBoundsInfo();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume6 = composerStartRestartGroup.consume(localLayoutDirection3);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i38 = i36 >> 3;
                            FlingBehavior flingBehavior6 = flingBehavior;
                            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda3, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics3, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState3, beyondBoundsInfo3, z10, (LayoutDirection) objConsume6, orientation, z11, composerStartRestartGroup, (3670016 & i38) | (MutableVector.$stable << 6) | i219 | ((i36 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11, z10, flingBehavior6, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i36 << 3) & 1008) | (i37 & 7168) | (i38 & 57344) | (i38 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE3, composerStartRestartGroup, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z11;
                            composer2 = composerStartRestartGroup;
                            modifier4 = modifier3;
                            paddingValues4 = paddingValues7;
                            f3 = f9;
                            f4 = f10;
                            flingBehavior3 = flingBehavior6;
                            z5 = z10;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                                public final void invoke(Composer composer3, int i39) {
                                    LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                                }
                            });
                        }
                    }
                    i4 |= 100663296;
                    i16 = i3 & 512;
                    if (i16 != 0) {
                        if ((i & BaseAdapter.HEADER) == 0) {
                            if (composerStartRestartGroup.changed(f2)) {
                                i17 = 536870912;
                            } else {
                                i17 = 268435456;
                            }
                            i4 |= i17;
                        }
                        if ((i3 & 1024) != 0) {
                            i18 = i2 | 6;
                        } else if ((i2 & 6) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i19 = 4;
                            } else {
                                i19 = 2;
                            }
                            i18 = i2 | i19;
                        } else {
                            i18 = i2;
                        }
                        i20 = i4;
                        if ((i20 & 306783379) == 306783378) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            } else {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                            }
                            int i2112 = i22 & 14;
                            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda4 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2112);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller4 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller4);
                                objRememberedValue = compositionScopedCoroutineScopeCanceller4;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CoroutineScope coroutineScope4 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext4 = CompositionLocalsKt.getLocalGraphicsContext();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume7 = composerStartRestartGroup.consume(localGraphicsContext4);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i2113 = i22 >> 6;
                            int i2114 = (i2113 & 896) | i2112;
                            int i2115 = i2113 & 7168;
                            int i2116 = i22 >> 9;
                            int i2117 = i2114 | i2115 | ((i22 << 9) & 57344) | (i2116 & 458752) | (i2116 & 3670016) | ((i22 << 18) & 234881024);
                            boolean z12 = z3;
                            int i39 = i22;
                            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE4 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda4, paddingValues3, z12, orientation, f, f2, coroutineScope4, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume7, composerStartRestartGroup, i2117);
                            PaddingValues paddingValues8 = paddingValues3;
                            float f11 = f;
                            float f12 = f2;
                            int i310 = i39 >> 12;
                            boolean z13 = z2;
                            Modifier modifierLazyLayoutSemantics4 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda4, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z12, composerStartRestartGroup, (i310 & 112) | i2112), orientation, z13, z12, composerStartRestartGroup, ((i39 << 6) & 7168) | (i2116 & 57344) | (i39 & 458752));
                            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState4 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2112);
                            LazyLayoutBeyondBoundsInfo beyondBoundsInfo4 = lazyStaggeredGridState.getBeyondBoundsInfo();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume8 = composerStartRestartGroup.consume(localLayoutDirection4);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i311 = i39 >> 3;
                            FlingBehavior flingBehavior7 = flingBehavior;
                            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda4, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics4, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState4, beyondBoundsInfo4, z12, (LayoutDirection) objConsume8, orientation, z13, composerStartRestartGroup, (3670016 & i311) | (MutableVector.$stable << 6) | i2115 | ((i39 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z13, z12, flingBehavior7, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i39 << 3) & 1008) | (i310 & 7168) | (i311 & 57344) | (i311 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE4, composerStartRestartGroup, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z13;
                            composer2 = composerStartRestartGroup;
                            modifier4 = modifier3;
                            paddingValues4 = paddingValues8;
                            f3 = f11;
                            f4 = f12;
                            flingBehavior3 = flingBehavior7;
                            z5 = z12;
                        } else {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            } else {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                            }
                            int i2118 = i22 & 14;
                            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda5 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2118);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller5 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller5);
                                objRememberedValue = compositionScopedCoroutineScopeCanceller5;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CoroutineScope coroutineScope5 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext5 = CompositionLocalsKt.getLocalGraphicsContext();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume9 = composerStartRestartGroup.consume(localGraphicsContext5);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i2119 = i22 >> 6;
                            int i21110 = (i2119 & 896) | i2118;
                            int i21111 = i2119 & 7168;
                            int i21112 = i22 >> 9;
                            int i21113 = i21110 | i21111 | ((i22 << 9) & 57344) | (i21112 & 458752) | (i21112 & 3670016) | ((i22 << 18) & 234881024);
                            boolean z14 = z3;
                            int i312 = i22;
                            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE5 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda5, paddingValues3, z14, orientation, f, f2, coroutineScope5, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume9, composerStartRestartGroup, i21113);
                            PaddingValues paddingValues9 = paddingValues3;
                            float f13 = f;
                            float f14 = f2;
                            int i313 = i312 >> 12;
                            boolean z15 = z2;
                            Modifier modifierLazyLayoutSemantics5 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda5, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z14, composerStartRestartGroup, (i313 & 112) | i2118), orientation, z15, z14, composerStartRestartGroup, ((i312 << 6) & 7168) | (i21112 & 57344) | (i312 & 458752));
                            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState5 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2118);
                            LazyLayoutBeyondBoundsInfo beyondBoundsInfo5 = lazyStaggeredGridState.getBeyondBoundsInfo();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection5 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume10 = composerStartRestartGroup.consume(localLayoutDirection5);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i314 = i312 >> 3;
                            FlingBehavior flingBehavior8 = flingBehavior;
                            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda5, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics5, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState5, beyondBoundsInfo5, z14, (LayoutDirection) objConsume10, orientation, z15, composerStartRestartGroup, (3670016 & i314) | (MutableVector.$stable << 6) | i21111 | ((i312 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z15, z14, flingBehavior8, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i312 << 3) & 1008) | (i313 & 7168) | (i314 & 57344) | (i314 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE5, composerStartRestartGroup, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z15;
                            composer2 = composerStartRestartGroup;
                            modifier4 = modifier3;
                            paddingValues4 = paddingValues9;
                            f3 = f13;
                            f4 = f14;
                            flingBehavior3 = flingBehavior8;
                            z5 = z14;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                                public final void invoke(Composer composer3, int i315) {
                                    LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                                }
                            });
                        }
                    }
                    i4 |= BaseAdapter.HEADER;
                    if ((i3 & 1024) != 0) {
                        i18 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i19 = 4;
                        } else {
                            i19 = 2;
                        }
                        i18 = i2 | i19;
                    } else {
                        i18 = i2;
                    }
                    i20 = i4;
                    if ((i20 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i21114 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda6 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21114);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller6 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller6);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller6;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope6 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext6 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11 = composerStartRestartGroup.consume(localGraphicsContext6);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i21115 = i22 >> 6;
                        int i21116 = (i21115 & 896) | i21114;
                        int i21117 = i21115 & 7168;
                        int i21118 = i22 >> 9;
                        int i21119 = i21116 | i21117 | ((i22 << 9) & 57344) | (i21118 & 458752) | (i21118 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z16 = z3;
                        int i315 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE6 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda6, paddingValues3, z16, orientation, f, f2, coroutineScope6, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11, composerStartRestartGroup, i21119);
                        PaddingValues paddingValues10 = paddingValues3;
                        float f15 = f;
                        float f16 = f2;
                        int i316 = i315 >> 12;
                        boolean z17 = z2;
                        Modifier modifierLazyLayoutSemantics6 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda6, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z16, composerStartRestartGroup, (i316 & 112) | i21114), orientation, z17, z16, composerStartRestartGroup, ((i315 << 6) & 7168) | (i21118 & 57344) | (i315 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState6 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21114);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo6 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection6 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume12 = composerStartRestartGroup.consume(localLayoutDirection6);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i317 = i315 >> 3;
                        FlingBehavior flingBehavior9 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda6, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics6, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState6, beyondBoundsInfo6, z16, (LayoutDirection) objConsume12, orientation, z17, composerStartRestartGroup, (3670016 & i317) | (MutableVector.$stable << 6) | i21117 | ((i315 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z17, z16, flingBehavior9, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i315 << 3) & 1008) | (i316 & 7168) | (i317 & 57344) | (i317 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE6, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z17;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues10;
                        f3 = f15;
                        f4 = f16;
                        flingBehavior3 = flingBehavior9;
                        z5 = z16;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i211110 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda7 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211110);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller7 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller7);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller7;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope7 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext7 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume13 = composerStartRestartGroup.consume(localGraphicsContext7);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i211111 = i22 >> 6;
                        int i211112 = (i211111 & 896) | i211110;
                        int i211113 = i211111 & 7168;
                        int i211114 = i22 >> 9;
                        int i211115 = i211112 | i211113 | ((i22 << 9) & 57344) | (i211114 & 458752) | (i211114 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z18 = z3;
                        int i318 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE7 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda7, paddingValues3, z18, orientation, f, f2, coroutineScope7, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume13, composerStartRestartGroup, i211115);
                        PaddingValues paddingValues11 = paddingValues3;
                        float f17 = f;
                        float f18 = f2;
                        int i319 = i318 >> 12;
                        boolean z19 = z2;
                        Modifier modifierLazyLayoutSemantics7 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda7, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z18, composerStartRestartGroup, (i319 & 112) | i211110), orientation, z19, z18, composerStartRestartGroup, ((i318 << 6) & 7168) | (i211114 & 57344) | (i318 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState7 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211110);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo7 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection7 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume14 = composerStartRestartGroup.consume(localLayoutDirection7);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i3110 = i318 >> 3;
                        FlingBehavior flingBehavior10 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda7, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics7, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState7, beyondBoundsInfo7, z18, (LayoutDirection) objConsume14, orientation, z19, composerStartRestartGroup, (3670016 & i3110) | (MutableVector.$stable << 6) | i211113 | ((i318 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z19, z18, flingBehavior10, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i318 << 3) & 1008) | (i319 & 7168) | (i3110 & 57344) | (i3110 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE7, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z19;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues11;
                        f3 = f17;
                        f4 = f18;
                        flingBehavior3 = flingBehavior10;
                        z5 = z18;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                            public final void invoke(Composer composer3, int i3111) {
                                LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= 24576;
                paddingValues2 = paddingValues;
                i10 = i3 & 32;
                if (i10 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    z3 = z;
                } else {
                    z3 = z;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(z3)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i4 |= i11;
                    }
                }
                if ((i & 1572864) != 0) {
                    if ((i3 & 64) == 0) {
                        i23 = 524288;
                    } else {
                        i23 = 524288;
                    }
                    i4 |= i23;
                }
                i12 = i3 & 128;
                if (i12 != 0) {
                    i4 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i13 = 8388608;
                    } else {
                        i13 = 4194304;
                    }
                    i4 |= i13;
                }
                i14 = i3 & 256;
                if (i14 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f)) {
                            i15 = 67108864;
                        } else {
                            i15 = 33554432;
                        }
                        i4 |= i15;
                    }
                    i16 = i3 & 512;
                    if (i16 != 0) {
                        if ((i & BaseAdapter.HEADER) == 0) {
                            if (composerStartRestartGroup.changed(f2)) {
                                i17 = 536870912;
                            } else {
                                i17 = 268435456;
                            }
                            i4 |= i17;
                        }
                        if ((i3 & 1024) != 0) {
                            i18 = i2 | 6;
                        } else if ((i2 & 6) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i19 = 4;
                            } else {
                                i19 = 2;
                            }
                            i18 = i2 | i19;
                        } else {
                            i18 = i2;
                        }
                        i20 = i4;
                        if ((i20 & 306783379) == 306783378) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            } else {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                            }
                            int i211116 = i22 & 14;
                            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda8 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211116);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller8 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller8);
                                objRememberedValue = compositionScopedCoroutineScopeCanceller8;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CoroutineScope coroutineScope8 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext8 = CompositionLocalsKt.getLocalGraphicsContext();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume15 = composerStartRestartGroup.consume(localGraphicsContext8);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i211117 = i22 >> 6;
                            int i211118 = (i211117 & 896) | i211116;
                            int i211119 = i211117 & 7168;
                            int i2111110 = i22 >> 9;
                            int i2111111 = i211118 | i211119 | ((i22 << 9) & 57344) | (i2111110 & 458752) | (i2111110 & 3670016) | ((i22 << 18) & 234881024);
                            boolean z110 = z3;
                            int i3111 = i22;
                            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE8 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda8, paddingValues3, z110, orientation, f, f2, coroutineScope8, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume15, composerStartRestartGroup, i2111111);
                            PaddingValues paddingValues12 = paddingValues3;
                            float f19 = f;
                            float f110 = f2;
                            int i3112 = i3111 >> 12;
                            boolean z111 = z2;
                            Modifier modifierLazyLayoutSemantics8 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda8, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z110, composerStartRestartGroup, (i3112 & 112) | i211116), orientation, z111, z110, composerStartRestartGroup, ((i3111 << 6) & 7168) | (i2111110 & 57344) | (i3111 & 458752));
                            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState8 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211116);
                            LazyLayoutBeyondBoundsInfo beyondBoundsInfo8 = lazyStaggeredGridState.getBeyondBoundsInfo();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection8 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume16 = composerStartRestartGroup.consume(localLayoutDirection8);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i3113 = i3111 >> 3;
                            FlingBehavior flingBehavior11 = flingBehavior;
                            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda8, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics8, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState8, beyondBoundsInfo8, z110, (LayoutDirection) objConsume16, orientation, z111, composerStartRestartGroup, (3670016 & i3113) | (MutableVector.$stable << 6) | i211119 | ((i3111 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111, z110, flingBehavior11, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111 << 3) & 1008) | (i3112 & 7168) | (i3113 & 57344) | (i3113 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE8, composerStartRestartGroup, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z111;
                            composer2 = composerStartRestartGroup;
                            modifier4 = modifier3;
                            paddingValues4 = paddingValues12;
                            f3 = f19;
                            f4 = f110;
                            flingBehavior3 = flingBehavior11;
                            z5 = z110;
                        } else {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            } else {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                            }
                            int i2111112 = i22 & 14;
                            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda9 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111112);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller9 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller9);
                                objRememberedValue = compositionScopedCoroutineScopeCanceller9;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CoroutineScope coroutineScope9 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext9 = CompositionLocalsKt.getLocalGraphicsContext();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume17 = composerStartRestartGroup.consume(localGraphicsContext9);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i2111113 = i22 >> 6;
                            int i2111114 = (i2111113 & 896) | i2111112;
                            int i2111115 = i2111113 & 7168;
                            int i2111116 = i22 >> 9;
                            int i2111117 = i2111114 | i2111115 | ((i22 << 9) & 57344) | (i2111116 & 458752) | (i2111116 & 3670016) | ((i22 << 18) & 234881024);
                            boolean z112 = z3;
                            int i3114 = i22;
                            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE9 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda9, paddingValues3, z112, orientation, f, f2, coroutineScope9, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume17, composerStartRestartGroup, i2111117);
                            PaddingValues paddingValues13 = paddingValues3;
                            float f111 = f;
                            float f112 = f2;
                            int i3115 = i3114 >> 12;
                            boolean z113 = z2;
                            Modifier modifierLazyLayoutSemantics9 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda9, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z112, composerStartRestartGroup, (i3115 & 112) | i2111112), orientation, z113, z112, composerStartRestartGroup, ((i3114 << 6) & 7168) | (i2111116 & 57344) | (i3114 & 458752));
                            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState9 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111112);
                            LazyLayoutBeyondBoundsInfo beyondBoundsInfo9 = lazyStaggeredGridState.getBeyondBoundsInfo();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection9 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume18 = composerStartRestartGroup.consume(localLayoutDirection9);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i3116 = i3114 >> 3;
                            FlingBehavior flingBehavior12 = flingBehavior;
                            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda9, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics9, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState9, beyondBoundsInfo9, z112, (LayoutDirection) objConsume18, orientation, z113, composerStartRestartGroup, (3670016 & i3116) | (MutableVector.$stable << 6) | i2111115 | ((i3114 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z113, z112, flingBehavior12, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3114 << 3) & 1008) | (i3115 & 7168) | (i3116 & 57344) | (i3116 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE9, composerStartRestartGroup, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z113;
                            composer2 = composerStartRestartGroup;
                            modifier4 = modifier3;
                            paddingValues4 = paddingValues13;
                            f3 = f111;
                            f4 = f112;
                            flingBehavior3 = flingBehavior12;
                            z5 = z112;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                                public final void invoke(Composer composer3, int i3117) {
                                    LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                                }
                            });
                        }
                    }
                    i4 |= BaseAdapter.HEADER;
                    if ((i3 & 1024) != 0) {
                        i18 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i19 = 4;
                        } else {
                            i19 = 2;
                        }
                        i18 = i2 | i19;
                    } else {
                        i18 = i2;
                    }
                    i20 = i4;
                    if ((i20 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i2111118 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda10 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111118);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller10 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller10);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller10;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope10 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext10 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume19 = composerStartRestartGroup.consume(localGraphicsContext10);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2111119 = i22 >> 6;
                        int i21111110 = (i2111119 & 896) | i2111118;
                        int i21111111 = i2111119 & 7168;
                        int i21111112 = i22 >> 9;
                        int i21111113 = i21111110 | i21111111 | ((i22 << 9) & 57344) | (i21111112 & 458752) | (i21111112 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z114 = z3;
                        int i3117 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE10 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda10, paddingValues3, z114, orientation, f, f2, coroutineScope10, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume19, composerStartRestartGroup, i21111113);
                        PaddingValues paddingValues14 = paddingValues3;
                        float f113 = f;
                        float f114 = f2;
                        int i3118 = i3117 >> 12;
                        boolean z115 = z2;
                        Modifier modifierLazyLayoutSemantics10 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda10, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z114, composerStartRestartGroup, (i3118 & 112) | i2111118), orientation, z115, z114, composerStartRestartGroup, ((i3117 << 6) & 7168) | (i21111112 & 57344) | (i3117 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState10 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111118);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo10 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection10 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume110 = composerStartRestartGroup.consume(localLayoutDirection10);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i3119 = i3117 >> 3;
                        FlingBehavior flingBehavior13 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda10, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics10, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState10, beyondBoundsInfo10, z114, (LayoutDirection) objConsume110, orientation, z115, composerStartRestartGroup, (3670016 & i3119) | (MutableVector.$stable << 6) | i21111111 | ((i3117 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z115, z114, flingBehavior13, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3117 << 3) & 1008) | (i3118 & 7168) | (i3119 & 57344) | (i3119 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE10, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z115;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues14;
                        f3 = f113;
                        f4 = f114;
                        flingBehavior3 = flingBehavior13;
                        z5 = z114;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i21111114 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111114);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller11;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope11 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume111 = composerStartRestartGroup.consume(localGraphicsContext11);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i21111115 = i22 >> 6;
                        int i21111116 = (i21111115 & 896) | i21111114;
                        int i21111117 = i21111115 & 7168;
                        int i21111118 = i22 >> 9;
                        int i21111119 = i21111116 | i21111117 | ((i22 << 9) & 57344) | (i21111118 & 458752) | (i21111118 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z116 = z3;
                        int i31110 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11, paddingValues3, z116, orientation, f, f2, coroutineScope11, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111, composerStartRestartGroup, i21111119);
                        PaddingValues paddingValues15 = paddingValues3;
                        float f115 = f;
                        float f116 = f2;
                        int i31111 = i31110 >> 12;
                        boolean z117 = z2;
                        Modifier modifierLazyLayoutSemantics11 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z116, composerStartRestartGroup, (i31111 & 112) | i21111114), orientation, z117, z116, composerStartRestartGroup, ((i31110 << 6) & 7168) | (i21111118 & 57344) | (i31110 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111114);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo11 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume112 = composerStartRestartGroup.consume(localLayoutDirection11);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i31112 = i31110 >> 3;
                        FlingBehavior flingBehavior14 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11, beyondBoundsInfo11, z116, (LayoutDirection) objConsume112, orientation, z117, composerStartRestartGroup, (3670016 & i31112) | (MutableVector.$stable << 6) | i21111117 | ((i31110 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z117, z116, flingBehavior14, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31110 << 3) & 1008) | (i31111 & 7168) | (i31112 & 57344) | (i31112 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z117;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues15;
                        f3 = f115;
                        f4 = f116;
                        flingBehavior3 = flingBehavior14;
                        z5 = z116;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                            public final void invoke(Composer composer3, int i31113) {
                                LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= 100663296;
                i16 = i3 & 512;
                if (i16 != 0) {
                    if ((i & BaseAdapter.HEADER) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i17 = 536870912;
                        } else {
                            i17 = 268435456;
                        }
                        i4 |= i17;
                    }
                    if ((i3 & 1024) != 0) {
                        i18 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i19 = 4;
                        } else {
                            i19 = 2;
                        }
                        i18 = i2 | i19;
                    } else {
                        i18 = i2;
                    }
                    i20 = i4;
                    if ((i20 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i211111110 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda12 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111110);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller12 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller12);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller12;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope12 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext12 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume113 = composerStartRestartGroup.consume(localGraphicsContext12);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i211111111 = i22 >> 6;
                        int i211111112 = (i211111111 & 896) | i211111110;
                        int i211111113 = i211111111 & 7168;
                        int i211111114 = i22 >> 9;
                        int i211111115 = i211111112 | i211111113 | ((i22 << 9) & 57344) | (i211111114 & 458752) | (i211111114 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z118 = z3;
                        int i31113 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE12 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda12, paddingValues3, z118, orientation, f, f2, coroutineScope12, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume113, composerStartRestartGroup, i211111115);
                        PaddingValues paddingValues16 = paddingValues3;
                        float f117 = f;
                        float f118 = f2;
                        int i31114 = i31113 >> 12;
                        boolean z119 = z2;
                        Modifier modifierLazyLayoutSemantics12 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda12, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z118, composerStartRestartGroup, (i31114 & 112) | i211111110), orientation, z119, z118, composerStartRestartGroup, ((i31113 << 6) & 7168) | (i211111114 & 57344) | (i31113 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState12 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111110);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo12 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection12 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume114 = composerStartRestartGroup.consume(localLayoutDirection12);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i31115 = i31113 >> 3;
                        FlingBehavior flingBehavior15 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda12, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics12, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState12, beyondBoundsInfo12, z118, (LayoutDirection) objConsume114, orientation, z119, composerStartRestartGroup, (3670016 & i31115) | (MutableVector.$stable << 6) | i211111113 | ((i31113 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z119, z118, flingBehavior15, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31113 << 3) & 1008) | (i31114 & 7168) | (i31115 & 57344) | (i31115 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE12, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z119;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues16;
                        f3 = f117;
                        f4 = f118;
                        flingBehavior3 = flingBehavior15;
                        z5 = z118;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i211111116 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda13 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111116);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller13 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller13);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller13;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope13 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext13 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume115 = composerStartRestartGroup.consume(localGraphicsContext13);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i211111117 = i22 >> 6;
                        int i211111118 = (i211111117 & 896) | i211111116;
                        int i211111119 = i211111117 & 7168;
                        int i2111111110 = i22 >> 9;
                        int i2111111111 = i211111118 | i211111119 | ((i22 << 9) & 57344) | (i2111111110 & 458752) | (i2111111110 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z1110 = z3;
                        int i31116 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE13 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda13, paddingValues3, z1110, orientation, f, f2, coroutineScope13, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume115, composerStartRestartGroup, i2111111111);
                        PaddingValues paddingValues17 = paddingValues3;
                        float f119 = f;
                        float f1110 = f2;
                        int i31117 = i31116 >> 12;
                        boolean z1111 = z2;
                        Modifier modifierLazyLayoutSemantics13 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda13, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1110, composerStartRestartGroup, (i31117 & 112) | i211111116), orientation, z1111, z1110, composerStartRestartGroup, ((i31116 << 6) & 7168) | (i2111111110 & 57344) | (i31116 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState13 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111116);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo13 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection13 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume116 = composerStartRestartGroup.consume(localLayoutDirection13);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i31118 = i31116 >> 3;
                        FlingBehavior flingBehavior16 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda13, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics13, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState13, beyondBoundsInfo13, z1110, (LayoutDirection) objConsume116, orientation, z1111, composerStartRestartGroup, (3670016 & i31118) | (MutableVector.$stable << 6) | i211111119 | ((i31116 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111, z1110, flingBehavior16, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31116 << 3) & 1008) | (i31117 & 7168) | (i31118 & 57344) | (i31118 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE13, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z1111;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues17;
                        f3 = f119;
                        f4 = f1110;
                        flingBehavior3 = flingBehavior16;
                        z5 = z1110;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                            public final void invoke(Composer composer3, int i31119) {
                                LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= BaseAdapter.HEADER;
                if ((i3 & 1024) != 0) {
                    i18 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i19 = 4;
                    } else {
                        i19 = 2;
                    }
                    i18 = i2 | i19;
                } else {
                    i18 = i2;
                }
                i20 = i4;
                if ((i20 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i2111111112 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda14 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111112);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller14 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller14);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller14;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope14 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext14 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume117 = composerStartRestartGroup.consume(localGraphicsContext14);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2111111113 = i22 >> 6;
                    int i2111111114 = (i2111111113 & 896) | i2111111112;
                    int i2111111115 = i2111111113 & 7168;
                    int i2111111116 = i22 >> 9;
                    int i2111111117 = i2111111114 | i2111111115 | ((i22 << 9) & 57344) | (i2111111116 & 458752) | (i2111111116 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z1112 = z3;
                    int i31119 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE14 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda14, paddingValues3, z1112, orientation, f, f2, coroutineScope14, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume117, composerStartRestartGroup, i2111111117);
                    PaddingValues paddingValues18 = paddingValues3;
                    float f1111 = f;
                    float f1112 = f2;
                    int i311110 = i31119 >> 12;
                    boolean z1113 = z2;
                    Modifier modifierLazyLayoutSemantics14 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda14, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1112, composerStartRestartGroup, (i311110 & 112) | i2111111112), orientation, z1113, z1112, composerStartRestartGroup, ((i31119 << 6) & 7168) | (i2111111116 & 57344) | (i31119 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState14 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111112);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo14 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection14 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume118 = composerStartRestartGroup.consume(localLayoutDirection14);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i311111 = i31119 >> 3;
                    FlingBehavior flingBehavior17 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda14, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics14, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState14, beyondBoundsInfo14, z1112, (LayoutDirection) objConsume118, orientation, z1113, composerStartRestartGroup, (3670016 & i311111) | (MutableVector.$stable << 6) | i2111111115 | ((i31119 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1113, z1112, flingBehavior17, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31119 << 3) & 1008) | (i311110 & 7168) | (i311111 & 57344) | (i311111 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE14, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z1113;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues18;
                    f3 = f1111;
                    f4 = f1112;
                    flingBehavior3 = flingBehavior17;
                    z5 = z1112;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i2111111118 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda15 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111118);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller15 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller15);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller15;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope15 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext15 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume119 = composerStartRestartGroup.consume(localGraphicsContext15);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2111111119 = i22 >> 6;
                    int i21111111110 = (i2111111119 & 896) | i2111111118;
                    int i21111111111 = i2111111119 & 7168;
                    int i21111111112 = i22 >> 9;
                    int i21111111113 = i21111111110 | i21111111111 | ((i22 << 9) & 57344) | (i21111111112 & 458752) | (i21111111112 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z1114 = z3;
                    int i311112 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE15 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda15, paddingValues3, z1114, orientation, f, f2, coroutineScope15, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume119, composerStartRestartGroup, i21111111113);
                    PaddingValues paddingValues19 = paddingValues3;
                    float f1113 = f;
                    float f1114 = f2;
                    int i311113 = i311112 >> 12;
                    boolean z1115 = z2;
                    Modifier modifierLazyLayoutSemantics15 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda15, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1114, composerStartRestartGroup, (i311113 & 112) | i2111111118), orientation, z1115, z1114, composerStartRestartGroup, ((i311112 << 6) & 7168) | (i21111111112 & 57344) | (i311112 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState15 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111118);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo15 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection15 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume1110 = composerStartRestartGroup.consume(localLayoutDirection15);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i311114 = i311112 >> 3;
                    FlingBehavior flingBehavior18 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda15, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics15, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState15, beyondBoundsInfo15, z1114, (LayoutDirection) objConsume1110, orientation, z1115, composerStartRestartGroup, (3670016 & i311114) | (MutableVector.$stable << 6) | i21111111111 | ((i311112 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1115, z1114, flingBehavior18, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311112 << 3) & 1008) | (i311113 & 7168) | (i311114 & 57344) | (i311114 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE15, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z1115;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues19;
                    f3 = f1113;
                    f4 = f1114;
                    flingBehavior3 = flingBehavior18;
                    z5 = z1114;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                        public final void invoke(Composer composer3, int i311115) {
                            LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= 3072;
            modifier2 = modifier;
            i8 = i3 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    paddingValues2 = paddingValues;
                    if (composerStartRestartGroup.changed(paddingValues2)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i4 |= i9;
                }
                i10 = i3 & 32;
                if (i10 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    z3 = z;
                } else {
                    z3 = z;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(z3)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i4 |= i11;
                    }
                }
                if ((i & 1572864) != 0) {
                    if ((i3 & 64) == 0) {
                        i23 = 524288;
                    } else {
                        i23 = 524288;
                    }
                    i4 |= i23;
                }
                i12 = i3 & 128;
                if (i12 != 0) {
                    i4 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i13 = 8388608;
                    } else {
                        i13 = 4194304;
                    }
                    i4 |= i13;
                }
                i14 = i3 & 256;
                if (i14 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f)) {
                            i15 = 67108864;
                        } else {
                            i15 = 33554432;
                        }
                        i4 |= i15;
                    }
                    i16 = i3 & 512;
                    if (i16 != 0) {
                        if ((i & BaseAdapter.HEADER) == 0) {
                            if (composerStartRestartGroup.changed(f2)) {
                                i17 = 536870912;
                            } else {
                                i17 = 268435456;
                            }
                            i4 |= i17;
                        }
                        if ((i3 & 1024) != 0) {
                            i18 = i2 | 6;
                        } else if ((i2 & 6) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i19 = 4;
                            } else {
                                i19 = 2;
                            }
                            i18 = i2 | i19;
                        } else {
                            i18 = i2;
                        }
                        i20 = i4;
                        if ((i20 & 306783379) == 306783378) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            } else {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                            }
                            int i21111111114 = i22 & 14;
                            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda16 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111111114);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller16 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller16);
                                objRememberedValue = compositionScopedCoroutineScopeCanceller16;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CoroutineScope coroutineScope16 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext16 = CompositionLocalsKt.getLocalGraphicsContext();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume1111 = composerStartRestartGroup.consume(localGraphicsContext16);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i21111111115 = i22 >> 6;
                            int i21111111116 = (i21111111115 & 896) | i21111111114;
                            int i21111111117 = i21111111115 & 7168;
                            int i21111111118 = i22 >> 9;
                            int i21111111119 = i21111111116 | i21111111117 | ((i22 << 9) & 57344) | (i21111111118 & 458752) | (i21111111118 & 3670016) | ((i22 << 18) & 234881024);
                            boolean z1116 = z3;
                            int i311115 = i22;
                            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE16 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda16, paddingValues3, z1116, orientation, f, f2, coroutineScope16, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111, composerStartRestartGroup, i21111111119);
                            PaddingValues paddingValues110 = paddingValues3;
                            float f1115 = f;
                            float f1116 = f2;
                            int i311116 = i311115 >> 12;
                            boolean z1117 = z2;
                            Modifier modifierLazyLayoutSemantics16 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda16, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1116, composerStartRestartGroup, (i311116 & 112) | i21111111114), orientation, z1117, z1116, composerStartRestartGroup, ((i311115 << 6) & 7168) | (i21111111118 & 57344) | (i311115 & 458752));
                            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState16 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111111114);
                            LazyLayoutBeyondBoundsInfo beyondBoundsInfo16 = lazyStaggeredGridState.getBeyondBoundsInfo();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection16 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume1112 = composerStartRestartGroup.consume(localLayoutDirection16);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i311117 = i311115 >> 3;
                            FlingBehavior flingBehavior19 = flingBehavior;
                            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda16, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics16, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState16, beyondBoundsInfo16, z1116, (LayoutDirection) objConsume1112, orientation, z1117, composerStartRestartGroup, (3670016 & i311117) | (MutableVector.$stable << 6) | i21111111117 | ((i311115 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1117, z1116, flingBehavior19, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311115 << 3) & 1008) | (i311116 & 7168) | (i311117 & 57344) | (i311117 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE16, composerStartRestartGroup, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z1117;
                            composer2 = composerStartRestartGroup;
                            modifier4 = modifier3;
                            paddingValues4 = paddingValues110;
                            f3 = f1115;
                            f4 = f1116;
                            flingBehavior3 = flingBehavior19;
                            z5 = z1116;
                        } else {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            } else {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                            }
                            int i211111111110 = i22 & 14;
                            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda17 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111110);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller17 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller17);
                                objRememberedValue = compositionScopedCoroutineScopeCanceller17;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CoroutineScope coroutineScope17 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext17 = CompositionLocalsKt.getLocalGraphicsContext();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume1113 = composerStartRestartGroup.consume(localGraphicsContext17);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i211111111111 = i22 >> 6;
                            int i211111111112 = (i211111111111 & 896) | i211111111110;
                            int i211111111113 = i211111111111 & 7168;
                            int i211111111114 = i22 >> 9;
                            int i211111111115 = i211111111112 | i211111111113 | ((i22 << 9) & 57344) | (i211111111114 & 458752) | (i211111111114 & 3670016) | ((i22 << 18) & 234881024);
                            boolean z1118 = z3;
                            int i311118 = i22;
                            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE17 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda17, paddingValues3, z1118, orientation, f, f2, coroutineScope17, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1113, composerStartRestartGroup, i211111111115);
                            PaddingValues paddingValues111 = paddingValues3;
                            float f1117 = f;
                            float f1118 = f2;
                            int i311119 = i311118 >> 12;
                            boolean z1119 = z2;
                            Modifier modifierLazyLayoutSemantics17 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda17, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1118, composerStartRestartGroup, (i311119 & 112) | i211111111110), orientation, z1119, z1118, composerStartRestartGroup, ((i311118 << 6) & 7168) | (i211111111114 & 57344) | (i311118 & 458752));
                            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState17 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111110);
                            LazyLayoutBeyondBoundsInfo beyondBoundsInfo17 = lazyStaggeredGridState.getBeyondBoundsInfo();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection17 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume1114 = composerStartRestartGroup.consume(localLayoutDirection17);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i3111110 = i311118 >> 3;
                            FlingBehavior flingBehavior110 = flingBehavior;
                            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda17, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics17, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState17, beyondBoundsInfo17, z1118, (LayoutDirection) objConsume1114, orientation, z1119, composerStartRestartGroup, (3670016 & i3111110) | (MutableVector.$stable << 6) | i211111111113 | ((i311118 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1119, z1118, flingBehavior110, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311118 << 3) & 1008) | (i311119 & 7168) | (i3111110 & 57344) | (i3111110 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE17, composerStartRestartGroup, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z1119;
                            composer2 = composerStartRestartGroup;
                            modifier4 = modifier3;
                            paddingValues4 = paddingValues111;
                            f3 = f1117;
                            f4 = f1118;
                            flingBehavior3 = flingBehavior110;
                            z5 = z1118;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                                public final void invoke(Composer composer3, int i3111111) {
                                    LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                                }
                            });
                        }
                    }
                    i4 |= BaseAdapter.HEADER;
                    if ((i3 & 1024) != 0) {
                        i18 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i19 = 4;
                        } else {
                            i19 = 2;
                        }
                        i18 = i2 | i19;
                    } else {
                        i18 = i2;
                    }
                    i20 = i4;
                    if ((i20 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i211111111116 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda18 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111116);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller18 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller18);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller18;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope18 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext18 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1115 = composerStartRestartGroup.consume(localGraphicsContext18);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i211111111117 = i22 >> 6;
                        int i211111111118 = (i211111111117 & 896) | i211111111116;
                        int i211111111119 = i211111111117 & 7168;
                        int i2111111111110 = i22 >> 9;
                        int i2111111111111 = i211111111118 | i211111111119 | ((i22 << 9) & 57344) | (i2111111111110 & 458752) | (i2111111111110 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z11110 = z3;
                        int i3111111 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE18 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda18, paddingValues3, z11110, orientation, f, f2, coroutineScope18, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1115, composerStartRestartGroup, i2111111111111);
                        PaddingValues paddingValues112 = paddingValues3;
                        float f1119 = f;
                        float f11110 = f2;
                        int i3111112 = i3111111 >> 12;
                        boolean z11111 = z2;
                        Modifier modifierLazyLayoutSemantics18 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda18, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11110, composerStartRestartGroup, (i3111112 & 112) | i211111111116), orientation, z11111, z11110, composerStartRestartGroup, ((i3111111 << 6) & 7168) | (i2111111111110 & 57344) | (i3111111 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState18 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111116);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo18 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection18 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1116 = composerStartRestartGroup.consume(localLayoutDirection18);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i3111113 = i3111111 >> 3;
                        FlingBehavior flingBehavior111 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda18, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics18, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState18, beyondBoundsInfo18, z11110, (LayoutDirection) objConsume1116, orientation, z11111, composerStartRestartGroup, (3670016 & i3111113) | (MutableVector.$stable << 6) | i211111111119 | ((i3111111 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111, z11110, flingBehavior111, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111 << 3) & 1008) | (i3111112 & 7168) | (i3111113 & 57344) | (i3111113 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE18, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z11111;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues112;
                        f3 = f1119;
                        f4 = f11110;
                        flingBehavior3 = flingBehavior111;
                        z5 = z11110;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i2111111111112 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda19 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111112);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller19 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller19);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller19;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope19 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext19 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1117 = composerStartRestartGroup.consume(localGraphicsContext19);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2111111111113 = i22 >> 6;
                        int i2111111111114 = (i2111111111113 & 896) | i2111111111112;
                        int i2111111111115 = i2111111111113 & 7168;
                        int i2111111111116 = i22 >> 9;
                        int i2111111111117 = i2111111111114 | i2111111111115 | ((i22 << 9) & 57344) | (i2111111111116 & 458752) | (i2111111111116 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z11112 = z3;
                        int i3111114 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE19 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda19, paddingValues3, z11112, orientation, f, f2, coroutineScope19, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1117, composerStartRestartGroup, i2111111111117);
                        PaddingValues paddingValues113 = paddingValues3;
                        float f11111 = f;
                        float f11112 = f2;
                        int i3111115 = i3111114 >> 12;
                        boolean z11113 = z2;
                        Modifier modifierLazyLayoutSemantics19 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda19, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11112, composerStartRestartGroup, (i3111115 & 112) | i2111111111112), orientation, z11113, z11112, composerStartRestartGroup, ((i3111114 << 6) & 7168) | (i2111111111116 & 57344) | (i3111114 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState19 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111112);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo19 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection19 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1118 = composerStartRestartGroup.consume(localLayoutDirection19);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i3111116 = i3111114 >> 3;
                        FlingBehavior flingBehavior112 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda19, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics19, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState19, beyondBoundsInfo19, z11112, (LayoutDirection) objConsume1118, orientation, z11113, composerStartRestartGroup, (3670016 & i3111116) | (MutableVector.$stable << 6) | i2111111111115 | ((i3111114 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11113, z11112, flingBehavior112, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111114 << 3) & 1008) | (i3111115 & 7168) | (i3111116 & 57344) | (i3111116 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE19, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z11113;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues113;
                        f3 = f11111;
                        f4 = f11112;
                        flingBehavior3 = flingBehavior112;
                        z5 = z11112;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                            public final void invoke(Composer composer3, int i3111117) {
                                LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= 100663296;
                i16 = i3 & 512;
                if (i16 != 0) {
                    if ((i & BaseAdapter.HEADER) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i17 = 536870912;
                        } else {
                            i17 = 268435456;
                        }
                        i4 |= i17;
                    }
                    if ((i3 & 1024) != 0) {
                        i18 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i19 = 4;
                        } else {
                            i19 = 2;
                        }
                        i18 = i2 | i19;
                    } else {
                        i18 = i2;
                    }
                    i20 = i4;
                    if ((i20 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i2111111111118 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda110 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111118);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller110 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller110);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller110;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope110 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext110 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1119 = composerStartRestartGroup.consume(localGraphicsContext110);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2111111111119 = i22 >> 6;
                        int i21111111111110 = (i2111111111119 & 896) | i2111111111118;
                        int i21111111111111 = i2111111111119 & 7168;
                        int i21111111111112 = i22 >> 9;
                        int i21111111111113 = i21111111111110 | i21111111111111 | ((i22 << 9) & 57344) | (i21111111111112 & 458752) | (i21111111111112 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z11114 = z3;
                        int i3111117 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE110 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda110, paddingValues3, z11114, orientation, f, f2, coroutineScope110, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1119, composerStartRestartGroup, i21111111111113);
                        PaddingValues paddingValues114 = paddingValues3;
                        float f11113 = f;
                        float f11114 = f2;
                        int i3111118 = i3111117 >> 12;
                        boolean z11115 = z2;
                        Modifier modifierLazyLayoutSemantics110 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda110, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11114, composerStartRestartGroup, (i3111118 & 112) | i2111111111118), orientation, z11115, z11114, composerStartRestartGroup, ((i3111117 << 6) & 7168) | (i21111111111112 & 57344) | (i3111117 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState110 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111118);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo110 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection110 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11110 = composerStartRestartGroup.consume(localLayoutDirection110);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i3111119 = i3111117 >> 3;
                        FlingBehavior flingBehavior113 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda110, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics110, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState110, beyondBoundsInfo110, z11114, (LayoutDirection) objConsume11110, orientation, z11115, composerStartRestartGroup, (3670016 & i3111119) | (MutableVector.$stable << 6) | i21111111111111 | ((i3111117 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11115, z11114, flingBehavior113, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111117 << 3) & 1008) | (i3111118 & 7168) | (i3111119 & 57344) | (i3111119 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE110, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z11115;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues114;
                        f3 = f11113;
                        f4 = f11114;
                        flingBehavior3 = flingBehavior113;
                        z5 = z11114;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i21111111111114 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111111111114);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller111;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope111 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11111 = composerStartRestartGroup.consume(localGraphicsContext111);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i21111111111115 = i22 >> 6;
                        int i21111111111116 = (i21111111111115 & 896) | i21111111111114;
                        int i21111111111117 = i21111111111115 & 7168;
                        int i21111111111118 = i22 >> 9;
                        int i21111111111119 = i21111111111116 | i21111111111117 | ((i22 << 9) & 57344) | (i21111111111118 & 458752) | (i21111111111118 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z11116 = z3;
                        int i31111110 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111, paddingValues3, z11116, orientation, f, f2, coroutineScope111, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111, composerStartRestartGroup, i21111111111119);
                        PaddingValues paddingValues115 = paddingValues3;
                        float f11115 = f;
                        float f11116 = f2;
                        int i31111111 = i31111110 >> 12;
                        boolean z11117 = z2;
                        Modifier modifierLazyLayoutSemantics111 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11116, composerStartRestartGroup, (i31111111 & 112) | i21111111111114), orientation, z11117, z11116, composerStartRestartGroup, ((i31111110 << 6) & 7168) | (i21111111111118 & 57344) | (i31111110 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111111111114);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo111 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11112 = composerStartRestartGroup.consume(localLayoutDirection111);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i31111112 = i31111110 >> 3;
                        FlingBehavior flingBehavior114 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111, beyondBoundsInfo111, z11116, (LayoutDirection) objConsume11112, orientation, z11117, composerStartRestartGroup, (3670016 & i31111112) | (MutableVector.$stable << 6) | i21111111111117 | ((i31111110 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11117, z11116, flingBehavior114, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111110 << 3) & 1008) | (i31111111 & 7168) | (i31111112 & 57344) | (i31111112 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z11117;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues115;
                        f3 = f11115;
                        f4 = f11116;
                        flingBehavior3 = flingBehavior114;
                        z5 = z11116;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                            public final void invoke(Composer composer3, int i31111113) {
                                LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= BaseAdapter.HEADER;
                if ((i3 & 1024) != 0) {
                    i18 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i19 = 4;
                    } else {
                        i19 = 2;
                    }
                    i18 = i2 | i19;
                } else {
                    i18 = i2;
                }
                i20 = i4;
                if ((i20 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i211111111111110 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda112 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111110);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller112 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller112);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller112;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope112 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext112 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11113 = composerStartRestartGroup.consume(localGraphicsContext112);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211111111111111 = i22 >> 6;
                    int i211111111111112 = (i211111111111111 & 896) | i211111111111110;
                    int i211111111111113 = i211111111111111 & 7168;
                    int i211111111111114 = i22 >> 9;
                    int i211111111111115 = i211111111111112 | i211111111111113 | ((i22 << 9) & 57344) | (i211111111111114 & 458752) | (i211111111111114 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z11118 = z3;
                    int i31111113 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE112 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda112, paddingValues3, z11118, orientation, f, f2, coroutineScope112, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11113, composerStartRestartGroup, i211111111111115);
                    PaddingValues paddingValues116 = paddingValues3;
                    float f11117 = f;
                    float f11118 = f2;
                    int i31111114 = i31111113 >> 12;
                    boolean z11119 = z2;
                    Modifier modifierLazyLayoutSemantics112 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda112, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11118, composerStartRestartGroup, (i31111114 & 112) | i211111111111110), orientation, z11119, z11118, composerStartRestartGroup, ((i31111113 << 6) & 7168) | (i211111111111114 & 57344) | (i31111113 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState112 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111110);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo112 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection112 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11114 = composerStartRestartGroup.consume(localLayoutDirection112);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i31111115 = i31111113 >> 3;
                    FlingBehavior flingBehavior115 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda112, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics112, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState112, beyondBoundsInfo112, z11118, (LayoutDirection) objConsume11114, orientation, z11119, composerStartRestartGroup, (3670016 & i31111115) | (MutableVector.$stable << 6) | i211111111111113 | ((i31111113 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11119, z11118, flingBehavior115, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111113 << 3) & 1008) | (i31111114 & 7168) | (i31111115 & 57344) | (i31111115 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE112, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z11119;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues116;
                    f3 = f11117;
                    f4 = f11118;
                    flingBehavior3 = flingBehavior115;
                    z5 = z11118;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i211111111111116 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda113 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111116);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller113 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller113);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller113;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope113 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext113 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11115 = composerStartRestartGroup.consume(localGraphicsContext113);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211111111111117 = i22 >> 6;
                    int i211111111111118 = (i211111111111117 & 896) | i211111111111116;
                    int i211111111111119 = i211111111111117 & 7168;
                    int i2111111111111110 = i22 >> 9;
                    int i2111111111111111 = i211111111111118 | i211111111111119 | ((i22 << 9) & 57344) | (i2111111111111110 & 458752) | (i2111111111111110 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z111110 = z3;
                    int i31111116 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE113 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda113, paddingValues3, z111110, orientation, f, f2, coroutineScope113, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11115, composerStartRestartGroup, i2111111111111111);
                    PaddingValues paddingValues117 = paddingValues3;
                    float f11119 = f;
                    float f111110 = f2;
                    int i31111117 = i31111116 >> 12;
                    boolean z111111 = z2;
                    Modifier modifierLazyLayoutSemantics113 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda113, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111110, composerStartRestartGroup, (i31111117 & 112) | i211111111111116), orientation, z111111, z111110, composerStartRestartGroup, ((i31111116 << 6) & 7168) | (i2111111111111110 & 57344) | (i31111116 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState113 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111116);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo113 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection113 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11116 = composerStartRestartGroup.consume(localLayoutDirection113);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i31111118 = i31111116 >> 3;
                    FlingBehavior flingBehavior116 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda113, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics113, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState113, beyondBoundsInfo113, z111110, (LayoutDirection) objConsume11116, orientation, z111111, composerStartRestartGroup, (3670016 & i31111118) | (MutableVector.$stable << 6) | i211111111111119 | ((i31111116 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111, z111110, flingBehavior116, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111116 << 3) & 1008) | (i31111117 & 7168) | (i31111118 & 57344) | (i31111118 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE113, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z111111;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues117;
                    f3 = f11119;
                    f4 = f111110;
                    flingBehavior3 = flingBehavior116;
                    z5 = z111110;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                        public final void invoke(Composer composer3, int i31111119) {
                            LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            paddingValues2 = paddingValues;
            i10 = i3 & 32;
            if (i10 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z3 = z;
            } else {
                z3 = z;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i4 |= i11;
                }
            }
            if ((i & 1572864) != 0) {
                if ((i3 & 64) == 0) {
                    i23 = 524288;
                } else {
                    i23 = 524288;
                }
                i4 |= i23;
            }
            i12 = i3 & 128;
            if (i12 != 0) {
                i4 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i13 = 8388608;
                } else {
                    i13 = 4194304;
                }
                i4 |= i13;
            }
            i14 = i3 & 256;
            if (i14 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f)) {
                        i15 = 67108864;
                    } else {
                        i15 = 33554432;
                    }
                    i4 |= i15;
                }
                i16 = i3 & 512;
                if (i16 != 0) {
                    if ((i & BaseAdapter.HEADER) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i17 = 536870912;
                        } else {
                            i17 = 268435456;
                        }
                        i4 |= i17;
                    }
                    if ((i3 & 1024) != 0) {
                        i18 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i19 = 4;
                        } else {
                            i19 = 2;
                        }
                        i18 = i2 | i19;
                    } else {
                        i18 = i2;
                    }
                    i20 = i4;
                    if ((i20 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i2111111111111112 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda114 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111112);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller114 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller114);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller114;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope114 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext114 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11117 = composerStartRestartGroup.consume(localGraphicsContext114);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2111111111111113 = i22 >> 6;
                        int i2111111111111114 = (i2111111111111113 & 896) | i2111111111111112;
                        int i2111111111111115 = i2111111111111113 & 7168;
                        int i2111111111111116 = i22 >> 9;
                        int i2111111111111117 = i2111111111111114 | i2111111111111115 | ((i22 << 9) & 57344) | (i2111111111111116 & 458752) | (i2111111111111116 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z111112 = z3;
                        int i31111119 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE114 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda114, paddingValues3, z111112, orientation, f, f2, coroutineScope114, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11117, composerStartRestartGroup, i2111111111111117);
                        PaddingValues paddingValues118 = paddingValues3;
                        float f111111 = f;
                        float f111112 = f2;
                        int i311111110 = i31111119 >> 12;
                        boolean z111113 = z2;
                        Modifier modifierLazyLayoutSemantics114 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda114, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111112, composerStartRestartGroup, (i311111110 & 112) | i2111111111111112), orientation, z111113, z111112, composerStartRestartGroup, ((i31111119 << 6) & 7168) | (i2111111111111116 & 57344) | (i31111119 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState114 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111112);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo114 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection114 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11118 = composerStartRestartGroup.consume(localLayoutDirection114);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i311111111 = i31111119 >> 3;
                        FlingBehavior flingBehavior117 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda114, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics114, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState114, beyondBoundsInfo114, z111112, (LayoutDirection) objConsume11118, orientation, z111113, composerStartRestartGroup, (3670016 & i311111111) | (MutableVector.$stable << 6) | i2111111111111115 | ((i31111119 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111113, z111112, flingBehavior117, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111119 << 3) & 1008) | (i311111110 & 7168) | (i311111111 & 57344) | (i311111111 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE114, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z111113;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues118;
                        f3 = f111111;
                        f4 = f111112;
                        flingBehavior3 = flingBehavior117;
                        z5 = z111112;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i2111111111111118 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda115 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111118);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller115 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller115);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller115;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope115 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext115 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11119 = composerStartRestartGroup.consume(localGraphicsContext115);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2111111111111119 = i22 >> 6;
                        int i21111111111111110 = (i2111111111111119 & 896) | i2111111111111118;
                        int i21111111111111111 = i2111111111111119 & 7168;
                        int i21111111111111112 = i22 >> 9;
                        int i21111111111111113 = i21111111111111110 | i21111111111111111 | ((i22 << 9) & 57344) | (i21111111111111112 & 458752) | (i21111111111111112 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z111114 = z3;
                        int i311111112 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE115 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda115, paddingValues3, z111114, orientation, f, f2, coroutineScope115, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11119, composerStartRestartGroup, i21111111111111113);
                        PaddingValues paddingValues119 = paddingValues3;
                        float f111113 = f;
                        float f111114 = f2;
                        int i311111113 = i311111112 >> 12;
                        boolean z111115 = z2;
                        Modifier modifierLazyLayoutSemantics115 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda115, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111114, composerStartRestartGroup, (i311111113 & 112) | i2111111111111118), orientation, z111115, z111114, composerStartRestartGroup, ((i311111112 << 6) & 7168) | (i21111111111111112 & 57344) | (i311111112 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState115 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111118);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo115 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection115 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume111110 = composerStartRestartGroup.consume(localLayoutDirection115);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i311111114 = i311111112 >> 3;
                        FlingBehavior flingBehavior118 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda115, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics115, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState115, beyondBoundsInfo115, z111114, (LayoutDirection) objConsume111110, orientation, z111115, composerStartRestartGroup, (3670016 & i311111114) | (MutableVector.$stable << 6) | i21111111111111111 | ((i311111112 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111115, z111114, flingBehavior118, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111112 << 3) & 1008) | (i311111113 & 7168) | (i311111114 & 57344) | (i311111114 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE115, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z111115;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues119;
                        f3 = f111113;
                        f4 = f111114;
                        flingBehavior3 = flingBehavior118;
                        z5 = z111114;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                            public final void invoke(Composer composer3, int i311111115) {
                                LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= BaseAdapter.HEADER;
                if ((i3 & 1024) != 0) {
                    i18 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i19 = 4;
                    } else {
                        i19 = 2;
                    }
                    i18 = i2 | i19;
                } else {
                    i18 = i2;
                }
                i20 = i4;
                if ((i20 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i21111111111111114 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda116 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111111111111114);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller116 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller116);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller116;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope116 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext116 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111 = composerStartRestartGroup.consume(localGraphicsContext116);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21111111111111115 = i22 >> 6;
                    int i21111111111111116 = (i21111111111111115 & 896) | i21111111111111114;
                    int i21111111111111117 = i21111111111111115 & 7168;
                    int i21111111111111118 = i22 >> 9;
                    int i21111111111111119 = i21111111111111116 | i21111111111111117 | ((i22 << 9) & 57344) | (i21111111111111118 & 458752) | (i21111111111111118 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z111116 = z3;
                    int i311111115 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE116 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda116, paddingValues3, z111116, orientation, f, f2, coroutineScope116, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111, composerStartRestartGroup, i21111111111111119);
                    PaddingValues paddingValues1110 = paddingValues3;
                    float f111115 = f;
                    float f111116 = f2;
                    int i311111116 = i311111115 >> 12;
                    boolean z111117 = z2;
                    Modifier modifierLazyLayoutSemantics116 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda116, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111116, composerStartRestartGroup, (i311111116 & 112) | i21111111111111114), orientation, z111117, z111116, composerStartRestartGroup, ((i311111115 << 6) & 7168) | (i21111111111111118 & 57344) | (i311111115 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState116 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111111111111114);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo116 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection116 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111112 = composerStartRestartGroup.consume(localLayoutDirection116);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i311111117 = i311111115 >> 3;
                    FlingBehavior flingBehavior119 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda116, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics116, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState116, beyondBoundsInfo116, z111116, (LayoutDirection) objConsume111112, orientation, z111117, composerStartRestartGroup, (3670016 & i311111117) | (MutableVector.$stable << 6) | i21111111111111117 | ((i311111115 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111117, z111116, flingBehavior119, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111115 << 3) & 1008) | (i311111116 & 7168) | (i311111117 & 57344) | (i311111117 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE116, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z111117;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues1110;
                    f3 = f111115;
                    f4 = f111116;
                    flingBehavior3 = flingBehavior119;
                    z5 = z111116;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i211111111111111110 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda117 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111110);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller117 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller117);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller117;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope117 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext117 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111113 = composerStartRestartGroup.consume(localGraphicsContext117);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211111111111111111 = i22 >> 6;
                    int i211111111111111112 = (i211111111111111111 & 896) | i211111111111111110;
                    int i211111111111111113 = i211111111111111111 & 7168;
                    int i211111111111111114 = i22 >> 9;
                    int i211111111111111115 = i211111111111111112 | i211111111111111113 | ((i22 << 9) & 57344) | (i211111111111111114 & 458752) | (i211111111111111114 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z111118 = z3;
                    int i311111118 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE117 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda117, paddingValues3, z111118, orientation, f, f2, coroutineScope117, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111113, composerStartRestartGroup, i211111111111111115);
                    PaddingValues paddingValues1111 = paddingValues3;
                    float f111117 = f;
                    float f111118 = f2;
                    int i311111119 = i311111118 >> 12;
                    boolean z111119 = z2;
                    Modifier modifierLazyLayoutSemantics117 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda117, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111118, composerStartRestartGroup, (i311111119 & 112) | i211111111111111110), orientation, z111119, z111118, composerStartRestartGroup, ((i311111118 << 6) & 7168) | (i211111111111111114 & 57344) | (i311111118 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState117 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111110);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo117 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection117 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111114 = composerStartRestartGroup.consume(localLayoutDirection117);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i3111111110 = i311111118 >> 3;
                    FlingBehavior flingBehavior1110 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda117, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics117, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState117, beyondBoundsInfo117, z111118, (LayoutDirection) objConsume111114, orientation, z111119, composerStartRestartGroup, (3670016 & i3111111110) | (MutableVector.$stable << 6) | i211111111111111113 | ((i311111118 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111119, z111118, flingBehavior1110, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111118 << 3) & 1008) | (i311111119 & 7168) | (i3111111110 & 57344) | (i3111111110 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE117, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z111119;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues1111;
                    f3 = f111117;
                    f4 = f111118;
                    flingBehavior3 = flingBehavior1110;
                    z5 = z111118;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                        public final void invoke(Composer composer3, int i3111111111) {
                            LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= 100663296;
            i16 = i3 & 512;
            if (i16 != 0) {
                if ((i & BaseAdapter.HEADER) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i17 = 536870912;
                    } else {
                        i17 = 268435456;
                    }
                    i4 |= i17;
                }
                if ((i3 & 1024) != 0) {
                    i18 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i19 = 4;
                    } else {
                        i19 = 2;
                    }
                    i18 = i2 | i19;
                } else {
                    i18 = i2;
                }
                i20 = i4;
                if ((i20 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i211111111111111116 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda118 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111116);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller118 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller118);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller118;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope118 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext118 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111115 = composerStartRestartGroup.consume(localGraphicsContext118);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211111111111111117 = i22 >> 6;
                    int i211111111111111118 = (i211111111111111117 & 896) | i211111111111111116;
                    int i211111111111111119 = i211111111111111117 & 7168;
                    int i2111111111111111110 = i22 >> 9;
                    int i2111111111111111111 = i211111111111111118 | i211111111111111119 | ((i22 << 9) & 57344) | (i2111111111111111110 & 458752) | (i2111111111111111110 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z1111110 = z3;
                    int i3111111111 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE118 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda118, paddingValues3, z1111110, orientation, f, f2, coroutineScope118, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111115, composerStartRestartGroup, i2111111111111111111);
                    PaddingValues paddingValues1112 = paddingValues3;
                    float f111119 = f;
                    float f1111110 = f2;
                    int i3111111112 = i3111111111 >> 12;
                    boolean z1111111 = z2;
                    Modifier modifierLazyLayoutSemantics118 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda118, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111110, composerStartRestartGroup, (i3111111112 & 112) | i211111111111111116), orientation, z1111111, z1111110, composerStartRestartGroup, ((i3111111111 << 6) & 7168) | (i2111111111111111110 & 57344) | (i3111111111 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState118 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111116);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo118 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection118 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111116 = composerStartRestartGroup.consume(localLayoutDirection118);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i3111111113 = i3111111111 >> 3;
                    FlingBehavior flingBehavior1111 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda118, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics118, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState118, beyondBoundsInfo118, z1111110, (LayoutDirection) objConsume111116, orientation, z1111111, composerStartRestartGroup, (3670016 & i3111111113) | (MutableVector.$stable << 6) | i211111111111111119 | ((i3111111111 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111, z1111110, flingBehavior1111, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111111 << 3) & 1008) | (i3111111112 & 7168) | (i3111111113 & 57344) | (i3111111113 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE118, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z1111111;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues1112;
                    f3 = f111119;
                    f4 = f1111110;
                    flingBehavior3 = flingBehavior1111;
                    z5 = z1111110;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i2111111111111111112 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda119 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111112);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller119 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller119);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller119;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope119 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext119 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111117 = composerStartRestartGroup.consume(localGraphicsContext119);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2111111111111111113 = i22 >> 6;
                    int i2111111111111111114 = (i2111111111111111113 & 896) | i2111111111111111112;
                    int i2111111111111111115 = i2111111111111111113 & 7168;
                    int i2111111111111111116 = i22 >> 9;
                    int i2111111111111111117 = i2111111111111111114 | i2111111111111111115 | ((i22 << 9) & 57344) | (i2111111111111111116 & 458752) | (i2111111111111111116 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z1111112 = z3;
                    int i3111111114 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE119 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda119, paddingValues3, z1111112, orientation, f, f2, coroutineScope119, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111117, composerStartRestartGroup, i2111111111111111117);
                    PaddingValues paddingValues1113 = paddingValues3;
                    float f1111111 = f;
                    float f1111112 = f2;
                    int i3111111115 = i3111111114 >> 12;
                    boolean z1111113 = z2;
                    Modifier modifierLazyLayoutSemantics119 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda119, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111112, composerStartRestartGroup, (i3111111115 & 112) | i2111111111111111112), orientation, z1111113, z1111112, composerStartRestartGroup, ((i3111111114 << 6) & 7168) | (i2111111111111111116 & 57344) | (i3111111114 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState119 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111112);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo119 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection119 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111118 = composerStartRestartGroup.consume(localLayoutDirection119);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i3111111116 = i3111111114 >> 3;
                    FlingBehavior flingBehavior1112 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda119, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics119, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState119, beyondBoundsInfo119, z1111112, (LayoutDirection) objConsume111118, orientation, z1111113, composerStartRestartGroup, (3670016 & i3111111116) | (MutableVector.$stable << 6) | i2111111111111111115 | ((i3111111114 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111113, z1111112, flingBehavior1112, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111114 << 3) & 1008) | (i3111111115 & 7168) | (i3111111116 & 57344) | (i3111111116 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE119, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z1111113;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues1113;
                    f3 = f1111111;
                    f4 = f1111112;
                    flingBehavior3 = flingBehavior1112;
                    z5 = z1111112;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                        public final void invoke(Composer composer3, int i3111111117) {
                            LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= BaseAdapter.HEADER;
            if ((i3 & 1024) != 0) {
                i18 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i19 = 4;
                } else {
                    i19 = 2;
                }
                i18 = i2 | i19;
            } else {
                i18 = i2;
            }
            i20 = i4;
            if ((i20 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                } else {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                }
                int i2111111111111111118 = i22 & 14;
                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1110 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111118);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1110 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1110);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller1110;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CoroutineScope coroutineScope1110 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1110 = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume111119 = composerStartRestartGroup.consume(localGraphicsContext1110);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2111111111111111119 = i22 >> 6;
                int i21111111111111111110 = (i2111111111111111119 & 896) | i2111111111111111118;
                int i21111111111111111111 = i2111111111111111119 & 7168;
                int i21111111111111111112 = i22 >> 9;
                int i21111111111111111113 = i21111111111111111110 | i21111111111111111111 | ((i22 << 9) & 57344) | (i21111111111111111112 & 458752) | (i21111111111111111112 & 3670016) | ((i22 << 18) & 234881024);
                boolean z1111114 = z3;
                int i3111111117 = i22;
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1110 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1110, paddingValues3, z1111114, orientation, f, f2, coroutineScope1110, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111119, composerStartRestartGroup, i21111111111111111113);
                PaddingValues paddingValues1114 = paddingValues3;
                float f1111113 = f;
                float f1111114 = f2;
                int i3111111118 = i3111111117 >> 12;
                boolean z1111115 = z2;
                Modifier modifierLazyLayoutSemantics1110 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1110, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111114, composerStartRestartGroup, (i3111111118 & 112) | i2111111111111111118), orientation, z1111115, z1111114, composerStartRestartGroup, ((i3111111117 << 6) & 7168) | (i21111111111111111112 & 57344) | (i3111111117 & 458752));
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1110 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111118);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo1110 = lazyStaggeredGridState.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1110 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111110 = composerStartRestartGroup.consume(localLayoutDirection1110);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i3111111119 = i3111111117 >> 3;
                FlingBehavior flingBehavior1113 = flingBehavior;
                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1110, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1110, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1110, beyondBoundsInfo1110, z1111114, (LayoutDirection) objConsume1111110, orientation, z1111115, composerStartRestartGroup, (3670016 & i3111111119) | (MutableVector.$stable << 6) | i21111111111111111111 | ((i3111111117 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111115, z1111114, flingBehavior1113, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111117 << 3) & 1008) | (i3111111118 & 7168) | (i3111111119 & 57344) | (i3111111119 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1110, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z1111115;
                composer2 = composerStartRestartGroup;
                modifier4 = modifier3;
                paddingValues4 = paddingValues1114;
                f3 = f1111113;
                f4 = f1111114;
                flingBehavior3 = flingBehavior1113;
                z5 = z1111114;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                } else {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                }
                int i21111111111111111114 = i22 & 14;
                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1111 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111111111111111114);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1111 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1111);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller1111;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CoroutineScope coroutineScope1111 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1111 = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111111 = composerStartRestartGroup.consume(localGraphicsContext1111);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i21111111111111111115 = i22 >> 6;
                int i21111111111111111116 = (i21111111111111111115 & 896) | i21111111111111111114;
                int i21111111111111111117 = i21111111111111111115 & 7168;
                int i21111111111111111118 = i22 >> 9;
                int i21111111111111111119 = i21111111111111111116 | i21111111111111111117 | ((i22 << 9) & 57344) | (i21111111111111111118 & 458752) | (i21111111111111111118 & 3670016) | ((i22 << 18) & 234881024);
                boolean z1111116 = z3;
                int i31111111110 = i22;
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1111 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1111, paddingValues3, z1111116, orientation, f, f2, coroutineScope1111, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111111, composerStartRestartGroup, i21111111111111111119);
                PaddingValues paddingValues1115 = paddingValues3;
                float f1111115 = f;
                float f1111116 = f2;
                int i31111111111 = i31111111110 >> 12;
                boolean z1111117 = z2;
                Modifier modifierLazyLayoutSemantics1111 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1111, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111116, composerStartRestartGroup, (i31111111111 & 112) | i21111111111111111114), orientation, z1111117, z1111116, composerStartRestartGroup, ((i31111111110 << 6) & 7168) | (i21111111111111111118 & 57344) | (i31111111110 & 458752));
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1111 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111111111111111114);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo1111 = lazyStaggeredGridState.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1111 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111112 = composerStartRestartGroup.consume(localLayoutDirection1111);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i31111111112 = i31111111110 >> 3;
                FlingBehavior flingBehavior1114 = flingBehavior;
                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1111, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1111, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1111, beyondBoundsInfo1111, z1111116, (LayoutDirection) objConsume1111112, orientation, z1111117, composerStartRestartGroup, (3670016 & i31111111112) | (MutableVector.$stable << 6) | i21111111111111111117 | ((i31111111110 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111117, z1111116, flingBehavior1114, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111110 << 3) & 1008) | (i31111111111 & 7168) | (i31111111112 & 57344) | (i31111111112 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1111, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z1111117;
                composer2 = composerStartRestartGroup;
                modifier4 = modifier3;
                paddingValues4 = paddingValues1115;
                f3 = f1111115;
                f4 = f1111116;
                flingBehavior3 = flingBehavior1114;
                z5 = z1111116;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                    public final void invoke(Composer composer3, int i31111111113) {
                        LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i4 |= 48;
        if ((i3 & 4) != 0) {
            i4 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i & 512) == 0) {
                zChangedInstance = composerStartRestartGroup.changed(lazyGridStaggeredGridSlotsProvider);
            } else {
                zChangedInstance = composerStartRestartGroup.changedInstance(lazyGridStaggeredGridSlotsProvider);
            }
            if (zChangedInstance) {
                i5 = 256;
            } else {
                i5 = 128;
            }
            i4 |= i5;
        }
        i6 = i3 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i4 |= i7;
            }
            i8 = i3 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    paddingValues2 = paddingValues;
                    if (composerStartRestartGroup.changed(paddingValues2)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i4 |= i9;
                }
                i10 = i3 & 32;
                if (i10 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    z3 = z;
                } else {
                    z3 = z;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(z3)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i4 |= i11;
                    }
                }
                if ((i & 1572864) != 0) {
                    if ((i3 & 64) == 0) {
                        i23 = 524288;
                    } else {
                        i23 = 524288;
                    }
                    i4 |= i23;
                }
                i12 = i3 & 128;
                if (i12 != 0) {
                    i4 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i13 = 8388608;
                    } else {
                        i13 = 4194304;
                    }
                    i4 |= i13;
                }
                i14 = i3 & 256;
                if (i14 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f)) {
                            i15 = 67108864;
                        } else {
                            i15 = 33554432;
                        }
                        i4 |= i15;
                    }
                    i16 = i3 & 512;
                    if (i16 != 0) {
                        if ((i & BaseAdapter.HEADER) == 0) {
                            if (composerStartRestartGroup.changed(f2)) {
                                i17 = 536870912;
                            } else {
                                i17 = 268435456;
                            }
                            i4 |= i17;
                        }
                        if ((i3 & 1024) != 0) {
                            i18 = i2 | 6;
                        } else if ((i2 & 6) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i19 = 4;
                            } else {
                                i19 = 2;
                            }
                            i18 = i2 | i19;
                        } else {
                            i18 = i2;
                        }
                        i20 = i4;
                        if ((i20 & 306783379) == 306783378) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            } else {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                            }
                            int i211111111111111111110 = i22 & 14;
                            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1112 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111110);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1112 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1112);
                                objRememberedValue = compositionScopedCoroutineScopeCanceller1112;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CoroutineScope coroutineScope1112 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1112 = CompositionLocalsKt.getLocalGraphicsContext();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume1111113 = composerStartRestartGroup.consume(localGraphicsContext1112);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i211111111111111111111 = i22 >> 6;
                            int i211111111111111111112 = (i211111111111111111111 & 896) | i211111111111111111110;
                            int i211111111111111111113 = i211111111111111111111 & 7168;
                            int i211111111111111111114 = i22 >> 9;
                            int i211111111111111111115 = i211111111111111111112 | i211111111111111111113 | ((i22 << 9) & 57344) | (i211111111111111111114 & 458752) | (i211111111111111111114 & 3670016) | ((i22 << 18) & 234881024);
                            boolean z1111118 = z3;
                            int i31111111113 = i22;
                            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1112 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1112, paddingValues3, z1111118, orientation, f, f2, coroutineScope1112, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111113, composerStartRestartGroup, i211111111111111111115);
                            PaddingValues paddingValues1116 = paddingValues3;
                            float f1111117 = f;
                            float f1111118 = f2;
                            int i31111111114 = i31111111113 >> 12;
                            boolean z1111119 = z2;
                            Modifier modifierLazyLayoutSemantics1112 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1112, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111118, composerStartRestartGroup, (i31111111114 & 112) | i211111111111111111110), orientation, z1111119, z1111118, composerStartRestartGroup, ((i31111111113 << 6) & 7168) | (i211111111111111111114 & 57344) | (i31111111113 & 458752));
                            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1112 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111110);
                            LazyLayoutBeyondBoundsInfo beyondBoundsInfo1112 = lazyStaggeredGridState.getBeyondBoundsInfo();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1112 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume1111114 = composerStartRestartGroup.consume(localLayoutDirection1112);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i31111111115 = i31111111113 >> 3;
                            FlingBehavior flingBehavior1115 = flingBehavior;
                            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1112, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1112, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1112, beyondBoundsInfo1112, z1111118, (LayoutDirection) objConsume1111114, orientation, z1111119, composerStartRestartGroup, (3670016 & i31111111115) | (MutableVector.$stable << 6) | i211111111111111111113 | ((i31111111113 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111119, z1111118, flingBehavior1115, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111113 << 3) & 1008) | (i31111111114 & 7168) | (i31111111115 & 57344) | (i31111111115 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1112, composerStartRestartGroup, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z1111119;
                            composer2 = composerStartRestartGroup;
                            modifier4 = modifier3;
                            paddingValues4 = paddingValues1116;
                            f3 = f1111117;
                            f4 = f1111118;
                            flingBehavior3 = flingBehavior1115;
                            z5 = z1111118;
                        } else {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            } else {
                                if (i6 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i8 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i10 != 0) {
                                    z3 = false;
                                }
                                if ((i3 & 64) != 0) {
                                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i21 = i20 & (-3670017);
                                } else {
                                    flingBehavior2 = flingBehavior;
                                    i21 = i20;
                                }
                                if (i12 != 0) {
                                    z2 = true;
                                } else {
                                    z2 = z2;
                                }
                                if (i14 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f;
                                }
                                if (i16 != 0) {
                                    f2 = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    f2 = f2;
                                }
                                flingBehavior = flingBehavior2;
                                i22 = i21;
                                f = fM6890constructorimpl;
                                modifier3 = companion;
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                            }
                            int i211111111111111111116 = i22 & 14;
                            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1113 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111116);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1113 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1113);
                                objRememberedValue = compositionScopedCoroutineScopeCanceller1113;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CoroutineScope coroutineScope1113 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1113 = CompositionLocalsKt.getLocalGraphicsContext();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume1111115 = composerStartRestartGroup.consume(localGraphicsContext1113);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i211111111111111111117 = i22 >> 6;
                            int i211111111111111111118 = (i211111111111111111117 & 896) | i211111111111111111116;
                            int i211111111111111111119 = i211111111111111111117 & 7168;
                            int i2111111111111111111110 = i22 >> 9;
                            int i2111111111111111111111 = i211111111111111111118 | i211111111111111111119 | ((i22 << 9) & 57344) | (i2111111111111111111110 & 458752) | (i2111111111111111111110 & 3670016) | ((i22 << 18) & 234881024);
                            boolean z11111110 = z3;
                            int i31111111116 = i22;
                            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1113 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1113, paddingValues3, z11111110, orientation, f, f2, coroutineScope1113, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111115, composerStartRestartGroup, i2111111111111111111111);
                            PaddingValues paddingValues1117 = paddingValues3;
                            float f1111119 = f;
                            float f11111110 = f2;
                            int i31111111117 = i31111111116 >> 12;
                            boolean z11111111 = z2;
                            Modifier modifierLazyLayoutSemantics1113 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1113, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111110, composerStartRestartGroup, (i31111111117 & 112) | i211111111111111111116), orientation, z11111111, z11111110, composerStartRestartGroup, ((i31111111116 << 6) & 7168) | (i2111111111111111111110 & 57344) | (i31111111116 & 458752));
                            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1113 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111116);
                            LazyLayoutBeyondBoundsInfo beyondBoundsInfo1113 = lazyStaggeredGridState.getBeyondBoundsInfo();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1113 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume1111116 = composerStartRestartGroup.consume(localLayoutDirection1113);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i31111111118 = i31111111116 >> 3;
                            FlingBehavior flingBehavior1116 = flingBehavior;
                            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1113, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1113, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1113, beyondBoundsInfo1113, z11111110, (LayoutDirection) objConsume1111116, orientation, z11111111, composerStartRestartGroup, (3670016 & i31111111118) | (MutableVector.$stable << 6) | i211111111111111111119 | ((i31111111116 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111111, z11111110, flingBehavior1116, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111116 << 3) & 1008) | (i31111111117 & 7168) | (i31111111118 & 57344) | (i31111111118 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1113, composerStartRestartGroup, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z11111111;
                            composer2 = composerStartRestartGroup;
                            modifier4 = modifier3;
                            paddingValues4 = paddingValues1117;
                            f3 = f1111119;
                            f4 = f11111110;
                            flingBehavior3 = flingBehavior1116;
                            z5 = z11111110;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                                public final void invoke(Composer composer3, int i31111111119) {
                                    LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                                }
                            });
                        }
                    }
                    i4 |= BaseAdapter.HEADER;
                    if ((i3 & 1024) != 0) {
                        i18 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i19 = 4;
                        } else {
                            i19 = 2;
                        }
                        i18 = i2 | i19;
                    } else {
                        i18 = i2;
                    }
                    i20 = i4;
                    if ((i20 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i2111111111111111111112 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1114 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111112);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1114 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1114);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller1114;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope1114 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1114 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1111117 = composerStartRestartGroup.consume(localGraphicsContext1114);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2111111111111111111113 = i22 >> 6;
                        int i2111111111111111111114 = (i2111111111111111111113 & 896) | i2111111111111111111112;
                        int i2111111111111111111115 = i2111111111111111111113 & 7168;
                        int i2111111111111111111116 = i22 >> 9;
                        int i2111111111111111111117 = i2111111111111111111114 | i2111111111111111111115 | ((i22 << 9) & 57344) | (i2111111111111111111116 & 458752) | (i2111111111111111111116 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z11111112 = z3;
                        int i31111111119 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1114 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1114, paddingValues3, z11111112, orientation, f, f2, coroutineScope1114, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111117, composerStartRestartGroup, i2111111111111111111117);
                        PaddingValues paddingValues1118 = paddingValues3;
                        float f11111111 = f;
                        float f11111112 = f2;
                        int i311111111110 = i31111111119 >> 12;
                        boolean z11111113 = z2;
                        Modifier modifierLazyLayoutSemantics1114 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1114, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111112, composerStartRestartGroup, (i311111111110 & 112) | i2111111111111111111112), orientation, z11111113, z11111112, composerStartRestartGroup, ((i31111111119 << 6) & 7168) | (i2111111111111111111116 & 57344) | (i31111111119 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1114 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111112);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo1114 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1114 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1111118 = composerStartRestartGroup.consume(localLayoutDirection1114);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i311111111111 = i31111111119 >> 3;
                        FlingBehavior flingBehavior1117 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1114, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1114, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1114, beyondBoundsInfo1114, z11111112, (LayoutDirection) objConsume1111118, orientation, z11111113, composerStartRestartGroup, (3670016 & i311111111111) | (MutableVector.$stable << 6) | i2111111111111111111115 | ((i31111111119 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111113, z11111112, flingBehavior1117, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111119 << 3) & 1008) | (i311111111110 & 7168) | (i311111111111 & 57344) | (i311111111111 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1114, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z11111113;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues1118;
                        f3 = f11111111;
                        f4 = f11111112;
                        flingBehavior3 = flingBehavior1117;
                        z5 = z11111112;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i2111111111111111111118 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1115 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111118);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1115 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1115);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller1115;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope1115 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1115 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1111119 = composerStartRestartGroup.consume(localGraphicsContext1115);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2111111111111111111119 = i22 >> 6;
                        int i21111111111111111111110 = (i2111111111111111111119 & 896) | i2111111111111111111118;
                        int i21111111111111111111111 = i2111111111111111111119 & 7168;
                        int i21111111111111111111112 = i22 >> 9;
                        int i21111111111111111111113 = i21111111111111111111110 | i21111111111111111111111 | ((i22 << 9) & 57344) | (i21111111111111111111112 & 458752) | (i21111111111111111111112 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z11111114 = z3;
                        int i311111111112 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1115 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1115, paddingValues3, z11111114, orientation, f, f2, coroutineScope1115, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111119, composerStartRestartGroup, i21111111111111111111113);
                        PaddingValues paddingValues1119 = paddingValues3;
                        float f11111113 = f;
                        float f11111114 = f2;
                        int i311111111113 = i311111111112 >> 12;
                        boolean z11111115 = z2;
                        Modifier modifierLazyLayoutSemantics1115 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1115, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111114, composerStartRestartGroup, (i311111111113 & 112) | i2111111111111111111118), orientation, z11111115, z11111114, composerStartRestartGroup, ((i311111111112 << 6) & 7168) | (i21111111111111111111112 & 57344) | (i311111111112 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1115 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111118);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo1115 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1115 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11111110 = composerStartRestartGroup.consume(localLayoutDirection1115);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i311111111114 = i311111111112 >> 3;
                        FlingBehavior flingBehavior1118 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1115, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1115, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1115, beyondBoundsInfo1115, z11111114, (LayoutDirection) objConsume11111110, orientation, z11111115, composerStartRestartGroup, (3670016 & i311111111114) | (MutableVector.$stable << 6) | i21111111111111111111111 | ((i311111111112 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111115, z11111114, flingBehavior1118, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111111112 << 3) & 1008) | (i311111111113 & 7168) | (i311111111114 & 57344) | (i311111111114 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1115, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z11111115;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues1119;
                        f3 = f11111113;
                        f4 = f11111114;
                        flingBehavior3 = flingBehavior1118;
                        z5 = z11111114;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                            public final void invoke(Composer composer3, int i311111111115) {
                                LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= 100663296;
                i16 = i3 & 512;
                if (i16 != 0) {
                    if ((i & BaseAdapter.HEADER) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i17 = 536870912;
                        } else {
                            i17 = 268435456;
                        }
                        i4 |= i17;
                    }
                    if ((i3 & 1024) != 0) {
                        i18 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i19 = 4;
                        } else {
                            i19 = 2;
                        }
                        i18 = i2 | i19;
                    } else {
                        i18 = i2;
                    }
                    i20 = i4;
                    if ((i20 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i21111111111111111111114 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1116 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111111111111111111114);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1116 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1116);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller1116;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope1116 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1116 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11111111 = composerStartRestartGroup.consume(localGraphicsContext1116);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i21111111111111111111115 = i22 >> 6;
                        int i21111111111111111111116 = (i21111111111111111111115 & 896) | i21111111111111111111114;
                        int i21111111111111111111117 = i21111111111111111111115 & 7168;
                        int i21111111111111111111118 = i22 >> 9;
                        int i21111111111111111111119 = i21111111111111111111116 | i21111111111111111111117 | ((i22 << 9) & 57344) | (i21111111111111111111118 & 458752) | (i21111111111111111111118 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z11111116 = z3;
                        int i311111111115 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1116 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1116, paddingValues3, z11111116, orientation, f, f2, coroutineScope1116, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111111, composerStartRestartGroup, i21111111111111111111119);
                        PaddingValues paddingValues11110 = paddingValues3;
                        float f11111115 = f;
                        float f11111116 = f2;
                        int i311111111116 = i311111111115 >> 12;
                        boolean z11111117 = z2;
                        Modifier modifierLazyLayoutSemantics1116 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1116, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111116, composerStartRestartGroup, (i311111111116 & 112) | i21111111111111111111114), orientation, z11111117, z11111116, composerStartRestartGroup, ((i311111111115 << 6) & 7168) | (i21111111111111111111118 & 57344) | (i311111111115 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1116 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111111111111111111114);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo1116 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1116 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11111112 = composerStartRestartGroup.consume(localLayoutDirection1116);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i311111111117 = i311111111115 >> 3;
                        FlingBehavior flingBehavior1119 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1116, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1116, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1116, beyondBoundsInfo1116, z11111116, (LayoutDirection) objConsume11111112, orientation, z11111117, composerStartRestartGroup, (3670016 & i311111111117) | (MutableVector.$stable << 6) | i21111111111111111111117 | ((i311111111115 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111117, z11111116, flingBehavior1119, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111111115 << 3) & 1008) | (i311111111116 & 7168) | (i311111111117 & 57344) | (i311111111117 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1116, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z11111117;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues11110;
                        f3 = f11111115;
                        f4 = f11111116;
                        flingBehavior3 = flingBehavior1119;
                        z5 = z11111116;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i211111111111111111111110 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1117 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111110);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1117 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1117);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller1117;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope1117 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1117 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11111113 = composerStartRestartGroup.consume(localGraphicsContext1117);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i211111111111111111111111 = i22 >> 6;
                        int i211111111111111111111112 = (i211111111111111111111111 & 896) | i211111111111111111111110;
                        int i211111111111111111111113 = i211111111111111111111111 & 7168;
                        int i211111111111111111111114 = i22 >> 9;
                        int i211111111111111111111115 = i211111111111111111111112 | i211111111111111111111113 | ((i22 << 9) & 57344) | (i211111111111111111111114 & 458752) | (i211111111111111111111114 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z11111118 = z3;
                        int i311111111118 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1117 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1117, paddingValues3, z11111118, orientation, f, f2, coroutineScope1117, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111113, composerStartRestartGroup, i211111111111111111111115);
                        PaddingValues paddingValues11111 = paddingValues3;
                        float f11111117 = f;
                        float f11111118 = f2;
                        int i311111111119 = i311111111118 >> 12;
                        boolean z11111119 = z2;
                        Modifier modifierLazyLayoutSemantics1117 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1117, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111118, composerStartRestartGroup, (i311111111119 & 112) | i211111111111111111111110), orientation, z11111119, z11111118, composerStartRestartGroup, ((i311111111118 << 6) & 7168) | (i211111111111111111111114 & 57344) | (i311111111118 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1117 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111110);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo1117 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1117 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11111114 = composerStartRestartGroup.consume(localLayoutDirection1117);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i3111111111110 = i311111111118 >> 3;
                        FlingBehavior flingBehavior11110 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1117, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1117, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1117, beyondBoundsInfo1117, z11111118, (LayoutDirection) objConsume11111114, orientation, z11111119, composerStartRestartGroup, (3670016 & i3111111111110) | (MutableVector.$stable << 6) | i211111111111111111111113 | ((i311111111118 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111119, z11111118, flingBehavior11110, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111111118 << 3) & 1008) | (i311111111119 & 7168) | (i3111111111110 & 57344) | (i3111111111110 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1117, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z11111119;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues11111;
                        f3 = f11111117;
                        f4 = f11111118;
                        flingBehavior3 = flingBehavior11110;
                        z5 = z11111118;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                            public final void invoke(Composer composer3, int i3111111111111) {
                                LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= BaseAdapter.HEADER;
                if ((i3 & 1024) != 0) {
                    i18 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i19 = 4;
                    } else {
                        i19 = 2;
                    }
                    i18 = i2 | i19;
                } else {
                    i18 = i2;
                }
                i20 = i4;
                if ((i20 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i211111111111111111111116 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1118 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111116);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1118 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1118);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller1118;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope1118 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1118 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111115 = composerStartRestartGroup.consume(localGraphicsContext1118);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211111111111111111111117 = i22 >> 6;
                    int i211111111111111111111118 = (i211111111111111111111117 & 896) | i211111111111111111111116;
                    int i211111111111111111111119 = i211111111111111111111117 & 7168;
                    int i2111111111111111111111110 = i22 >> 9;
                    int i2111111111111111111111111 = i211111111111111111111118 | i211111111111111111111119 | ((i22 << 9) & 57344) | (i2111111111111111111111110 & 458752) | (i2111111111111111111111110 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z111111110 = z3;
                    int i3111111111111 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1118 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1118, paddingValues3, z111111110, orientation, f, f2, coroutineScope1118, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111115, composerStartRestartGroup, i2111111111111111111111111);
                    PaddingValues paddingValues11112 = paddingValues3;
                    float f11111119 = f;
                    float f111111110 = f2;
                    int i3111111111112 = i3111111111111 >> 12;
                    boolean z111111111 = z2;
                    Modifier modifierLazyLayoutSemantics1118 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1118, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111111110, composerStartRestartGroup, (i3111111111112 & 112) | i211111111111111111111116), orientation, z111111111, z111111110, composerStartRestartGroup, ((i3111111111111 << 6) & 7168) | (i2111111111111111111111110 & 57344) | (i3111111111111 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1118 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111116);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo1118 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1118 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111116 = composerStartRestartGroup.consume(localLayoutDirection1118);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i3111111111113 = i3111111111111 >> 3;
                    FlingBehavior flingBehavior11111 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1118, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1118, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1118, beyondBoundsInfo1118, z111111110, (LayoutDirection) objConsume11111116, orientation, z111111111, composerStartRestartGroup, (3670016 & i3111111111113) | (MutableVector.$stable << 6) | i211111111111111111111119 | ((i3111111111111 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111111, z111111110, flingBehavior11111, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111111111 << 3) & 1008) | (i3111111111112 & 7168) | (i3111111111113 & 57344) | (i3111111111113 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1118, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z111111111;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues11112;
                    f3 = f11111119;
                    f4 = f111111110;
                    flingBehavior3 = flingBehavior11111;
                    z5 = z111111110;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i2111111111111111111111112 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1119 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111111112);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1119 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1119);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller1119;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope1119 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1119 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111117 = composerStartRestartGroup.consume(localGraphicsContext1119);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2111111111111111111111113 = i22 >> 6;
                    int i2111111111111111111111114 = (i2111111111111111111111113 & 896) | i2111111111111111111111112;
                    int i2111111111111111111111115 = i2111111111111111111111113 & 7168;
                    int i2111111111111111111111116 = i22 >> 9;
                    int i2111111111111111111111117 = i2111111111111111111111114 | i2111111111111111111111115 | ((i22 << 9) & 57344) | (i2111111111111111111111116 & 458752) | (i2111111111111111111111116 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z111111112 = z3;
                    int i3111111111114 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1119 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1119, paddingValues3, z111111112, orientation, f, f2, coroutineScope1119, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111117, composerStartRestartGroup, i2111111111111111111111117);
                    PaddingValues paddingValues11113 = paddingValues3;
                    float f111111111 = f;
                    float f111111112 = f2;
                    int i3111111111115 = i3111111111114 >> 12;
                    boolean z111111113 = z2;
                    Modifier modifierLazyLayoutSemantics1119 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1119, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111111112, composerStartRestartGroup, (i3111111111115 & 112) | i2111111111111111111111112), orientation, z111111113, z111111112, composerStartRestartGroup, ((i3111111111114 << 6) & 7168) | (i2111111111111111111111116 & 57344) | (i3111111111114 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1119 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111111112);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo1119 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1119 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111118 = composerStartRestartGroup.consume(localLayoutDirection1119);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i3111111111116 = i3111111111114 >> 3;
                    FlingBehavior flingBehavior11112 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1119, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1119, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1119, beyondBoundsInfo1119, z111111112, (LayoutDirection) objConsume11111118, orientation, z111111113, composerStartRestartGroup, (3670016 & i3111111111116) | (MutableVector.$stable << 6) | i2111111111111111111111115 | ((i3111111111114 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111113, z111111112, flingBehavior11112, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111111114 << 3) & 1008) | (i3111111111115 & 7168) | (i3111111111116 & 57344) | (i3111111111116 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1119, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z111111113;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues11113;
                    f3 = f111111111;
                    f4 = f111111112;
                    flingBehavior3 = flingBehavior11112;
                    z5 = z111111112;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                        public final void invoke(Composer composer3, int i3111111111117) {
                            LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            paddingValues2 = paddingValues;
            i10 = i3 & 32;
            if (i10 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z3 = z;
            } else {
                z3 = z;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i4 |= i11;
                }
            }
            if ((i & 1572864) != 0) {
                if ((i3 & 64) == 0) {
                    i23 = 524288;
                } else {
                    i23 = 524288;
                }
                i4 |= i23;
            }
            i12 = i3 & 128;
            if (i12 != 0) {
                i4 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i13 = 8388608;
                } else {
                    i13 = 4194304;
                }
                i4 |= i13;
            }
            i14 = i3 & 256;
            if (i14 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f)) {
                        i15 = 67108864;
                    } else {
                        i15 = 33554432;
                    }
                    i4 |= i15;
                }
                i16 = i3 & 512;
                if (i16 != 0) {
                    if ((i & BaseAdapter.HEADER) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i17 = 536870912;
                        } else {
                            i17 = 268435456;
                        }
                        i4 |= i17;
                    }
                    if ((i3 & 1024) != 0) {
                        i18 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i19 = 4;
                        } else {
                            i19 = 2;
                        }
                        i18 = i2 | i19;
                    } else {
                        i18 = i2;
                    }
                    i20 = i4;
                    if ((i20 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i2111111111111111111111118 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11110 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111111118);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11110 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11110);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller11110;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope11110 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11110 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11111119 = composerStartRestartGroup.consume(localGraphicsContext11110);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2111111111111111111111119 = i22 >> 6;
                        int i21111111111111111111111110 = (i2111111111111111111111119 & 896) | i2111111111111111111111118;
                        int i21111111111111111111111111 = i2111111111111111111111119 & 7168;
                        int i21111111111111111111111112 = i22 >> 9;
                        int i21111111111111111111111113 = i21111111111111111111111110 | i21111111111111111111111111 | ((i22 << 9) & 57344) | (i21111111111111111111111112 & 458752) | (i21111111111111111111111112 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z111111114 = z3;
                        int i3111111111117 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11110 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11110, paddingValues3, z111111114, orientation, f, f2, coroutineScope11110, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111119, composerStartRestartGroup, i21111111111111111111111113);
                        PaddingValues paddingValues11114 = paddingValues3;
                        float f111111113 = f;
                        float f111111114 = f2;
                        int i3111111111118 = i3111111111117 >> 12;
                        boolean z111111115 = z2;
                        Modifier modifierLazyLayoutSemantics11110 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11110, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111111114, composerStartRestartGroup, (i3111111111118 & 112) | i2111111111111111111111118), orientation, z111111115, z111111114, composerStartRestartGroup, ((i3111111111117 << 6) & 7168) | (i21111111111111111111111112 & 57344) | (i3111111111117 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11110 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111111118);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo11110 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11110 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume111111110 = composerStartRestartGroup.consume(localLayoutDirection11110);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i3111111111119 = i3111111111117 >> 3;
                        FlingBehavior flingBehavior11113 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11110, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11110, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11110, beyondBoundsInfo11110, z111111114, (LayoutDirection) objConsume111111110, orientation, z111111115, composerStartRestartGroup, (3670016 & i3111111111119) | (MutableVector.$stable << 6) | i21111111111111111111111111 | ((i3111111111117 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111115, z111111114, flingBehavior11113, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111111117 << 3) & 1008) | (i3111111111118 & 7168) | (i3111111111119 & 57344) | (i3111111111119 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11110, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z111111115;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues11114;
                        f3 = f111111113;
                        f4 = f111111114;
                        flingBehavior3 = flingBehavior11113;
                        z5 = z111111114;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i21111111111111111111111114 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11111 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111111111111111111111114);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11111 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11111);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller11111;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope11111 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11111 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume111111111 = composerStartRestartGroup.consume(localGraphicsContext11111);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i21111111111111111111111115 = i22 >> 6;
                        int i21111111111111111111111116 = (i21111111111111111111111115 & 896) | i21111111111111111111111114;
                        int i21111111111111111111111117 = i21111111111111111111111115 & 7168;
                        int i21111111111111111111111118 = i22 >> 9;
                        int i21111111111111111111111119 = i21111111111111111111111116 | i21111111111111111111111117 | ((i22 << 9) & 57344) | (i21111111111111111111111118 & 458752) | (i21111111111111111111111118 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z111111116 = z3;
                        int i31111111111110 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11111 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11111, paddingValues3, z111111116, orientation, f, f2, coroutineScope11111, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111111, composerStartRestartGroup, i21111111111111111111111119);
                        PaddingValues paddingValues11115 = paddingValues3;
                        float f111111115 = f;
                        float f111111116 = f2;
                        int i31111111111111 = i31111111111110 >> 12;
                        boolean z111111117 = z2;
                        Modifier modifierLazyLayoutSemantics11111 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11111, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111111116, composerStartRestartGroup, (i31111111111111 & 112) | i21111111111111111111111114), orientation, z111111117, z111111116, composerStartRestartGroup, ((i31111111111110 << 6) & 7168) | (i21111111111111111111111118 & 57344) | (i31111111111110 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11111 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111111111111111111111114);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo11111 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11111 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume111111112 = composerStartRestartGroup.consume(localLayoutDirection11111);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i31111111111112 = i31111111111110 >> 3;
                        FlingBehavior flingBehavior11114 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11111, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11111, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11111, beyondBoundsInfo11111, z111111116, (LayoutDirection) objConsume111111112, orientation, z111111117, composerStartRestartGroup, (3670016 & i31111111111112) | (MutableVector.$stable << 6) | i21111111111111111111111117 | ((i31111111111110 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111117, z111111116, flingBehavior11114, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111110 << 3) & 1008) | (i31111111111111 & 7168) | (i31111111111112 & 57344) | (i31111111111112 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11111, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z111111117;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues11115;
                        f3 = f111111115;
                        f4 = f111111116;
                        flingBehavior3 = flingBehavior11114;
                        z5 = z111111116;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                            public final void invoke(Composer composer3, int i31111111111113) {
                                LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= BaseAdapter.HEADER;
                if ((i3 & 1024) != 0) {
                    i18 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i19 = 4;
                    } else {
                        i19 = 2;
                    }
                    i18 = i2 | i19;
                } else {
                    i18 = i2;
                }
                i20 = i4;
                if ((i20 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i211111111111111111111111110 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11112 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111111110);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11112 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11112);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller11112;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope11112 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11112 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111113 = composerStartRestartGroup.consume(localGraphicsContext11112);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211111111111111111111111111 = i22 >> 6;
                    int i211111111111111111111111112 = (i211111111111111111111111111 & 896) | i211111111111111111111111110;
                    int i211111111111111111111111113 = i211111111111111111111111111 & 7168;
                    int i211111111111111111111111114 = i22 >> 9;
                    int i211111111111111111111111115 = i211111111111111111111111112 | i211111111111111111111111113 | ((i22 << 9) & 57344) | (i211111111111111111111111114 & 458752) | (i211111111111111111111111114 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z111111118 = z3;
                    int i31111111111113 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11112 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11112, paddingValues3, z111111118, orientation, f, f2, coroutineScope11112, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111113, composerStartRestartGroup, i211111111111111111111111115);
                    PaddingValues paddingValues11116 = paddingValues3;
                    float f111111117 = f;
                    float f111111118 = f2;
                    int i31111111111114 = i31111111111113 >> 12;
                    boolean z111111119 = z2;
                    Modifier modifierLazyLayoutSemantics11112 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11112, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111111118, composerStartRestartGroup, (i31111111111114 & 112) | i211111111111111111111111110), orientation, z111111119, z111111118, composerStartRestartGroup, ((i31111111111113 << 6) & 7168) | (i211111111111111111111111114 & 57344) | (i31111111111113 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11112 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111111110);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo11112 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11112 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111114 = composerStartRestartGroup.consume(localLayoutDirection11112);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i31111111111115 = i31111111111113 >> 3;
                    FlingBehavior flingBehavior11115 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11112, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11112, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11112, beyondBoundsInfo11112, z111111118, (LayoutDirection) objConsume111111114, orientation, z111111119, composerStartRestartGroup, (3670016 & i31111111111115) | (MutableVector.$stable << 6) | i211111111111111111111111113 | ((i31111111111113 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111119, z111111118, flingBehavior11115, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111113 << 3) & 1008) | (i31111111111114 & 7168) | (i31111111111115 & 57344) | (i31111111111115 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11112, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z111111119;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues11116;
                    f3 = f111111117;
                    f4 = f111111118;
                    flingBehavior3 = flingBehavior11115;
                    z5 = z111111118;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i211111111111111111111111116 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11113 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111111116);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11113 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11113);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller11113;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope11113 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11113 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111115 = composerStartRestartGroup.consume(localGraphicsContext11113);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211111111111111111111111117 = i22 >> 6;
                    int i211111111111111111111111118 = (i211111111111111111111111117 & 896) | i211111111111111111111111116;
                    int i211111111111111111111111119 = i211111111111111111111111117 & 7168;
                    int i2111111111111111111111111110 = i22 >> 9;
                    int i2111111111111111111111111111 = i211111111111111111111111118 | i211111111111111111111111119 | ((i22 << 9) & 57344) | (i2111111111111111111111111110 & 458752) | (i2111111111111111111111111110 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z1111111110 = z3;
                    int i31111111111116 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11113 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11113, paddingValues3, z1111111110, orientation, f, f2, coroutineScope11113, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111115, composerStartRestartGroup, i2111111111111111111111111111);
                    PaddingValues paddingValues11117 = paddingValues3;
                    float f111111119 = f;
                    float f1111111110 = f2;
                    int i31111111111117 = i31111111111116 >> 12;
                    boolean z1111111111 = z2;
                    Modifier modifierLazyLayoutSemantics11113 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11113, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111111110, composerStartRestartGroup, (i31111111111117 & 112) | i211111111111111111111111116), orientation, z1111111111, z1111111110, composerStartRestartGroup, ((i31111111111116 << 6) & 7168) | (i2111111111111111111111111110 & 57344) | (i31111111111116 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11113 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111111116);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo11113 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11113 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111116 = composerStartRestartGroup.consume(localLayoutDirection11113);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i31111111111118 = i31111111111116 >> 3;
                    FlingBehavior flingBehavior11116 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11113, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11113, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11113, beyondBoundsInfo11113, z1111111110, (LayoutDirection) objConsume111111116, orientation, z1111111111, composerStartRestartGroup, (3670016 & i31111111111118) | (MutableVector.$stable << 6) | i211111111111111111111111119 | ((i31111111111116 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111111, z1111111110, flingBehavior11116, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111116 << 3) & 1008) | (i31111111111117 & 7168) | (i31111111111118 & 57344) | (i31111111111118 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11113, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z1111111111;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues11117;
                    f3 = f111111119;
                    f4 = f1111111110;
                    flingBehavior3 = flingBehavior11116;
                    z5 = z1111111110;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                        public final void invoke(Composer composer3, int i31111111111119) {
                            LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= 100663296;
            i16 = i3 & 512;
            if (i16 != 0) {
                if ((i & BaseAdapter.HEADER) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i17 = 536870912;
                    } else {
                        i17 = 268435456;
                    }
                    i4 |= i17;
                }
                if ((i3 & 1024) != 0) {
                    i18 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i19 = 4;
                    } else {
                        i19 = 2;
                    }
                    i18 = i2 | i19;
                } else {
                    i18 = i2;
                }
                i20 = i4;
                if ((i20 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i2111111111111111111111111112 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11114 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111111111112);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11114 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11114);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller11114;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope11114 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11114 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111117 = composerStartRestartGroup.consume(localGraphicsContext11114);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2111111111111111111111111113 = i22 >> 6;
                    int i2111111111111111111111111114 = (i2111111111111111111111111113 & 896) | i2111111111111111111111111112;
                    int i2111111111111111111111111115 = i2111111111111111111111111113 & 7168;
                    int i2111111111111111111111111116 = i22 >> 9;
                    int i2111111111111111111111111117 = i2111111111111111111111111114 | i2111111111111111111111111115 | ((i22 << 9) & 57344) | (i2111111111111111111111111116 & 458752) | (i2111111111111111111111111116 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z1111111112 = z3;
                    int i31111111111119 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11114 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11114, paddingValues3, z1111111112, orientation, f, f2, coroutineScope11114, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111117, composerStartRestartGroup, i2111111111111111111111111117);
                    PaddingValues paddingValues11118 = paddingValues3;
                    float f1111111111 = f;
                    float f1111111112 = f2;
                    int i311111111111110 = i31111111111119 >> 12;
                    boolean z1111111113 = z2;
                    Modifier modifierLazyLayoutSemantics11114 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11114, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111111112, composerStartRestartGroup, (i311111111111110 & 112) | i2111111111111111111111111112), orientation, z1111111113, z1111111112, composerStartRestartGroup, ((i31111111111119 << 6) & 7168) | (i2111111111111111111111111116 & 57344) | (i31111111111119 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11114 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111111111112);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo11114 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11114 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111118 = composerStartRestartGroup.consume(localLayoutDirection11114);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i311111111111111 = i31111111111119 >> 3;
                    FlingBehavior flingBehavior11117 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11114, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11114, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11114, beyondBoundsInfo11114, z1111111112, (LayoutDirection) objConsume111111118, orientation, z1111111113, composerStartRestartGroup, (3670016 & i311111111111111) | (MutableVector.$stable << 6) | i2111111111111111111111111115 | ((i31111111111119 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111113, z1111111112, flingBehavior11117, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111119 << 3) & 1008) | (i311111111111110 & 7168) | (i311111111111111 & 57344) | (i311111111111111 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11114, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z1111111113;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues11118;
                    f3 = f1111111111;
                    f4 = f1111111112;
                    flingBehavior3 = flingBehavior11117;
                    z5 = z1111111112;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i2111111111111111111111111118 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11115 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111111111118);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11115 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11115);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller11115;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope11115 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11115 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111119 = composerStartRestartGroup.consume(localGraphicsContext11115);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2111111111111111111111111119 = i22 >> 6;
                    int i21111111111111111111111111110 = (i2111111111111111111111111119 & 896) | i2111111111111111111111111118;
                    int i21111111111111111111111111111 = i2111111111111111111111111119 & 7168;
                    int i21111111111111111111111111112 = i22 >> 9;
                    int i21111111111111111111111111113 = i21111111111111111111111111110 | i21111111111111111111111111111 | ((i22 << 9) & 57344) | (i21111111111111111111111111112 & 458752) | (i21111111111111111111111111112 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z1111111114 = z3;
                    int i311111111111112 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11115 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11115, paddingValues3, z1111111114, orientation, f, f2, coroutineScope11115, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111119, composerStartRestartGroup, i21111111111111111111111111113);
                    PaddingValues paddingValues11119 = paddingValues3;
                    float f1111111113 = f;
                    float f1111111114 = f2;
                    int i311111111111113 = i311111111111112 >> 12;
                    boolean z1111111115 = z2;
                    Modifier modifierLazyLayoutSemantics11115 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11115, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111111114, composerStartRestartGroup, (i311111111111113 & 112) | i2111111111111111111111111118), orientation, z1111111115, z1111111114, composerStartRestartGroup, ((i311111111111112 << 6) & 7168) | (i21111111111111111111111111112 & 57344) | (i311111111111112 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11115 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111111111118);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo11115 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11115 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume1111111110 = composerStartRestartGroup.consume(localLayoutDirection11115);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i311111111111114 = i311111111111112 >> 3;
                    FlingBehavior flingBehavior11118 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11115, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11115, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11115, beyondBoundsInfo11115, z1111111114, (LayoutDirection) objConsume1111111110, orientation, z1111111115, composerStartRestartGroup, (3670016 & i311111111111114) | (MutableVector.$stable << 6) | i21111111111111111111111111111 | ((i311111111111112 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111115, z1111111114, flingBehavior11118, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111111111112 << 3) & 1008) | (i311111111111113 & 7168) | (i311111111111114 & 57344) | (i311111111111114 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11115, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z1111111115;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues11119;
                    f3 = f1111111113;
                    f4 = f1111111114;
                    flingBehavior3 = flingBehavior11118;
                    z5 = z1111111114;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                        public final void invoke(Composer composer3, int i311111111111115) {
                            LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= BaseAdapter.HEADER;
            if ((i3 & 1024) != 0) {
                i18 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i19 = 4;
                } else {
                    i19 = 2;
                }
                i18 = i2 | i19;
            } else {
                i18 = i2;
            }
            i20 = i4;
            if ((i20 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                } else {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                }
                int i21111111111111111111111111114 = i22 & 14;
                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11116 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111111111111111111111111114);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11116 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11116);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller11116;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CoroutineScope coroutineScope11116 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11116 = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111111111 = composerStartRestartGroup.consume(localGraphicsContext11116);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i21111111111111111111111111115 = i22 >> 6;
                int i21111111111111111111111111116 = (i21111111111111111111111111115 & 896) | i21111111111111111111111111114;
                int i21111111111111111111111111117 = i21111111111111111111111111115 & 7168;
                int i21111111111111111111111111118 = i22 >> 9;
                int i21111111111111111111111111119 = i21111111111111111111111111116 | i21111111111111111111111111117 | ((i22 << 9) & 57344) | (i21111111111111111111111111118 & 458752) | (i21111111111111111111111111118 & 3670016) | ((i22 << 18) & 234881024);
                boolean z1111111116 = z3;
                int i311111111111115 = i22;
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11116 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11116, paddingValues3, z1111111116, orientation, f, f2, coroutineScope11116, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111111111, composerStartRestartGroup, i21111111111111111111111111119);
                PaddingValues paddingValues111110 = paddingValues3;
                float f1111111115 = f;
                float f1111111116 = f2;
                int i311111111111116 = i311111111111115 >> 12;
                boolean z1111111117 = z2;
                Modifier modifierLazyLayoutSemantics11116 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11116, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111111116, composerStartRestartGroup, (i311111111111116 & 112) | i21111111111111111111111111114), orientation, z1111111117, z1111111116, composerStartRestartGroup, ((i311111111111115 << 6) & 7168) | (i21111111111111111111111111118 & 57344) | (i311111111111115 & 458752));
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11116 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111111111111111111111111114);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo11116 = lazyStaggeredGridState.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11116 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111111112 = composerStartRestartGroup.consume(localLayoutDirection11116);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i311111111111117 = i311111111111115 >> 3;
                FlingBehavior flingBehavior11119 = flingBehavior;
                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11116, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11116, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11116, beyondBoundsInfo11116, z1111111116, (LayoutDirection) objConsume1111111112, orientation, z1111111117, composerStartRestartGroup, (3670016 & i311111111111117) | (MutableVector.$stable << 6) | i21111111111111111111111111117 | ((i311111111111115 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111117, z1111111116, flingBehavior11119, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111111111115 << 3) & 1008) | (i311111111111116 & 7168) | (i311111111111117 & 57344) | (i311111111111117 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11116, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z1111111117;
                composer2 = composerStartRestartGroup;
                modifier4 = modifier3;
                paddingValues4 = paddingValues111110;
                f3 = f1111111115;
                f4 = f1111111116;
                flingBehavior3 = flingBehavior11119;
                z5 = z1111111116;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                } else {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                }
                int i211111111111111111111111111110 = i22 & 14;
                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11117 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111111111110);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11117 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11117);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller11117;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CoroutineScope coroutineScope11117 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11117 = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111111113 = composerStartRestartGroup.consume(localGraphicsContext11117);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i211111111111111111111111111111 = i22 >> 6;
                int i211111111111111111111111111112 = (i211111111111111111111111111111 & 896) | i211111111111111111111111111110;
                int i211111111111111111111111111113 = i211111111111111111111111111111 & 7168;
                int i211111111111111111111111111114 = i22 >> 9;
                int i211111111111111111111111111115 = i211111111111111111111111111112 | i211111111111111111111111111113 | ((i22 << 9) & 57344) | (i211111111111111111111111111114 & 458752) | (i211111111111111111111111111114 & 3670016) | ((i22 << 18) & 234881024);
                boolean z1111111118 = z3;
                int i311111111111118 = i22;
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11117 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11117, paddingValues3, z1111111118, orientation, f, f2, coroutineScope11117, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111111113, composerStartRestartGroup, i211111111111111111111111111115);
                PaddingValues paddingValues111111 = paddingValues3;
                float f1111111117 = f;
                float f1111111118 = f2;
                int i311111111111119 = i311111111111118 >> 12;
                boolean z1111111119 = z2;
                Modifier modifierLazyLayoutSemantics11117 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11117, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111111118, composerStartRestartGroup, (i311111111111119 & 112) | i211111111111111111111111111110), orientation, z1111111119, z1111111118, composerStartRestartGroup, ((i311111111111118 << 6) & 7168) | (i211111111111111111111111111114 & 57344) | (i311111111111118 & 458752));
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11117 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111111111110);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo11117 = lazyStaggeredGridState.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11117 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111111114 = composerStartRestartGroup.consume(localLayoutDirection11117);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i3111111111111110 = i311111111111118 >> 3;
                FlingBehavior flingBehavior111110 = flingBehavior;
                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11117, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11117, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11117, beyondBoundsInfo11117, z1111111118, (LayoutDirection) objConsume1111111114, orientation, z1111111119, composerStartRestartGroup, (3670016 & i3111111111111110) | (MutableVector.$stable << 6) | i211111111111111111111111111113 | ((i311111111111118 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111119, z1111111118, flingBehavior111110, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111111111118 << 3) & 1008) | (i311111111111119 & 7168) | (i3111111111111110 & 57344) | (i3111111111111110 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11117, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z1111111119;
                composer2 = composerStartRestartGroup;
                modifier4 = modifier3;
                paddingValues4 = paddingValues111111;
                f3 = f1111111117;
                f4 = f1111111118;
                flingBehavior3 = flingBehavior111110;
                z5 = z1111111118;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                    public final void invoke(Composer composer3, int i3111111111111111) {
                        LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i4 |= 3072;
        modifier2 = modifier;
        i8 = i3 & 16;
        if (i8 != 0) {
            if ((i & 24576) == 0) {
                paddingValues2 = paddingValues;
                if (composerStartRestartGroup.changed(paddingValues2)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i4 |= i9;
            }
            i10 = i3 & 32;
            if (i10 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z3 = z;
            } else {
                z3 = z;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i4 |= i11;
                }
            }
            if ((i & 1572864) != 0) {
                if ((i3 & 64) == 0) {
                    i23 = 524288;
                } else {
                    i23 = 524288;
                }
                i4 |= i23;
            }
            i12 = i3 & 128;
            if (i12 != 0) {
                i4 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i13 = 8388608;
                } else {
                    i13 = 4194304;
                }
                i4 |= i13;
            }
            i14 = i3 & 256;
            if (i14 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f)) {
                        i15 = 67108864;
                    } else {
                        i15 = 33554432;
                    }
                    i4 |= i15;
                }
                i16 = i3 & 512;
                if (i16 != 0) {
                    if ((i & BaseAdapter.HEADER) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i17 = 536870912;
                        } else {
                            i17 = 268435456;
                        }
                        i4 |= i17;
                    }
                    if ((i3 & 1024) != 0) {
                        i18 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i19 = 4;
                        } else {
                            i19 = 2;
                        }
                        i18 = i2 | i19;
                    } else {
                        i18 = i2;
                    }
                    i20 = i4;
                    if ((i20 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i211111111111111111111111111116 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11118 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111111111116);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11118 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11118);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller11118;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope11118 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11118 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1111111115 = composerStartRestartGroup.consume(localGraphicsContext11118);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i211111111111111111111111111117 = i22 >> 6;
                        int i211111111111111111111111111118 = (i211111111111111111111111111117 & 896) | i211111111111111111111111111116;
                        int i211111111111111111111111111119 = i211111111111111111111111111117 & 7168;
                        int i2111111111111111111111111111110 = i22 >> 9;
                        int i2111111111111111111111111111111 = i211111111111111111111111111118 | i211111111111111111111111111119 | ((i22 << 9) & 57344) | (i2111111111111111111111111111110 & 458752) | (i2111111111111111111111111111110 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z11111111110 = z3;
                        int i3111111111111111 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11118 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11118, paddingValues3, z11111111110, orientation, f, f2, coroutineScope11118, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111111115, composerStartRestartGroup, i2111111111111111111111111111111);
                        PaddingValues paddingValues111112 = paddingValues3;
                        float f1111111119 = f;
                        float f11111111110 = f2;
                        int i3111111111111112 = i3111111111111111 >> 12;
                        boolean z11111111111 = z2;
                        Modifier modifierLazyLayoutSemantics11118 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11118, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111111110, composerStartRestartGroup, (i3111111111111112 & 112) | i211111111111111111111111111116), orientation, z11111111111, z11111111110, composerStartRestartGroup, ((i3111111111111111 << 6) & 7168) | (i2111111111111111111111111111110 & 57344) | (i3111111111111111 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11118 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111111111116);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo11118 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11118 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1111111116 = composerStartRestartGroup.consume(localLayoutDirection11118);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i3111111111111113 = i3111111111111111 >> 3;
                        FlingBehavior flingBehavior111111 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11118, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11118, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11118, beyondBoundsInfo11118, z11111111110, (LayoutDirection) objConsume1111111116, orientation, z11111111111, composerStartRestartGroup, (3670016 & i3111111111111113) | (MutableVector.$stable << 6) | i211111111111111111111111111119 | ((i3111111111111111 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111111111, z11111111110, flingBehavior111111, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111111111111 << 3) & 1008) | (i3111111111111112 & 7168) | (i3111111111111113 & 57344) | (i3111111111111113 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11118, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z11111111111;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues111112;
                        f3 = f1111111119;
                        f4 = f11111111110;
                        flingBehavior3 = flingBehavior111111;
                        z5 = z11111111110;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        } else {
                            if (i6 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i8 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i10 != 0) {
                                z3 = false;
                            }
                            if ((i3 & 64) != 0) {
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i21 = i20 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                i21 = i20;
                            }
                            if (i12 != 0) {
                                z2 = true;
                            } else {
                                z2 = z2;
                            }
                            if (i14 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f;
                            }
                            if (i16 != 0) {
                                f2 = Dp.m6890constructorimpl(0.0f);
                            } else {
                                f2 = f2;
                            }
                            flingBehavior = flingBehavior2;
                            i22 = i21;
                            f = fM6890constructorimpl;
                            modifier3 = companion;
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                        }
                        int i2111111111111111111111111111112 = i22 & 14;
                        Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda11119 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111111111111112);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11119 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11119);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller11119;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CoroutineScope coroutineScope11119 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext11119 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1111111117 = composerStartRestartGroup.consume(localGraphicsContext11119);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i2111111111111111111111111111113 = i22 >> 6;
                        int i2111111111111111111111111111114 = (i2111111111111111111111111111113 & 896) | i2111111111111111111111111111112;
                        int i2111111111111111111111111111115 = i2111111111111111111111111111113 & 7168;
                        int i2111111111111111111111111111116 = i22 >> 9;
                        int i2111111111111111111111111111117 = i2111111111111111111111111111114 | i2111111111111111111111111111115 | ((i22 << 9) & 57344) | (i2111111111111111111111111111116 & 458752) | (i2111111111111111111111111111116 & 3670016) | ((i22 << 18) & 234881024);
                        boolean z11111111112 = z3;
                        int i3111111111111114 = i22;
                        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11119 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda11119, paddingValues3, z11111111112, orientation, f, f2, coroutineScope11119, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111111117, composerStartRestartGroup, i2111111111111111111111111111117);
                        PaddingValues paddingValues111113 = paddingValues3;
                        float f11111111111 = f;
                        float f11111111112 = f2;
                        int i3111111111111115 = i3111111111111114 >> 12;
                        boolean z11111111113 = z2;
                        Modifier modifierLazyLayoutSemantics11119 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda11119, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111111112, composerStartRestartGroup, (i3111111111111115 & 112) | i2111111111111111111111111111112), orientation, z11111111113, z11111111112, composerStartRestartGroup, ((i3111111111111114 << 6) & 7168) | (i2111111111111111111111111111116 & 57344) | (i3111111111111114 & 458752));
                        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11119 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111111111111112);
                        LazyLayoutBeyondBoundsInfo beyondBoundsInfo11119 = lazyStaggeredGridState.getBeyondBoundsInfo();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11119 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume1111111118 = composerStartRestartGroup.consume(localLayoutDirection11119);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i3111111111111116 = i3111111111111114 >> 3;
                        FlingBehavior flingBehavior111112 = flingBehavior;
                        LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda11119, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics11119, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState11119, beyondBoundsInfo11119, z11111111112, (LayoutDirection) objConsume1111111118, orientation, z11111111113, composerStartRestartGroup, (3670016 & i3111111111111116) | (MutableVector.$stable << 6) | i2111111111111111111111111111115 | ((i3111111111111114 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111111113, z11111111112, flingBehavior111112, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111111111114 << 3) & 1008) | (i3111111111111115 & 7168) | (i3111111111111116 & 57344) | (i3111111111111116 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE11119, composerStartRestartGroup, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z11111111113;
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier3;
                        paddingValues4 = paddingValues111113;
                        f3 = f11111111111;
                        f4 = f11111111112;
                        flingBehavior3 = flingBehavior111112;
                        z5 = z11111111112;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                            public final void invoke(Composer composer3, int i3111111111111117) {
                                LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= BaseAdapter.HEADER;
                if ((i3 & 1024) != 0) {
                    i18 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i19 = 4;
                    } else {
                        i19 = 2;
                    }
                    i18 = i2 | i19;
                } else {
                    i18 = i2;
                }
                i20 = i4;
                if ((i20 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i2111111111111111111111111111118 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111110 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111111111111118);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111110 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111110);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller111110;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope111110 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111110 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume1111111119 = composerStartRestartGroup.consume(localGraphicsContext111110);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i2111111111111111111111111111119 = i22 >> 6;
                    int i21111111111111111111111111111110 = (i2111111111111111111111111111119 & 896) | i2111111111111111111111111111118;
                    int i21111111111111111111111111111111 = i2111111111111111111111111111119 & 7168;
                    int i21111111111111111111111111111112 = i22 >> 9;
                    int i21111111111111111111111111111113 = i21111111111111111111111111111110 | i21111111111111111111111111111111 | ((i22 << 9) & 57344) | (i21111111111111111111111111111112 & 458752) | (i21111111111111111111111111111112 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z11111111114 = z3;
                    int i3111111111111117 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111110 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111110, paddingValues3, z11111111114, orientation, f, f2, coroutineScope111110, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111111119, composerStartRestartGroup, i21111111111111111111111111111113);
                    PaddingValues paddingValues111114 = paddingValues3;
                    float f11111111113 = f;
                    float f11111111114 = f2;
                    int i3111111111111118 = i3111111111111117 >> 12;
                    boolean z11111111115 = z2;
                    Modifier modifierLazyLayoutSemantics111110 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111110, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111111114, composerStartRestartGroup, (i3111111111111118 & 112) | i2111111111111111111111111111118), orientation, z11111111115, z11111111114, composerStartRestartGroup, ((i3111111111111117 << 6) & 7168) | (i21111111111111111111111111111112 & 57344) | (i3111111111111117 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111110 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111111111111118);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo111110 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111110 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111111110 = composerStartRestartGroup.consume(localLayoutDirection111110);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i3111111111111119 = i3111111111111117 >> 3;
                    FlingBehavior flingBehavior111113 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111110, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111110, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111110, beyondBoundsInfo111110, z11111111114, (LayoutDirection) objConsume11111111110, orientation, z11111111115, composerStartRestartGroup, (3670016 & i3111111111111119) | (MutableVector.$stable << 6) | i21111111111111111111111111111111 | ((i3111111111111117 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111111115, z11111111114, flingBehavior111113, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111111111117 << 3) & 1008) | (i3111111111111118 & 7168) | (i3111111111111119 & 57344) | (i3111111111111119 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111110, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z11111111115;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues111114;
                    f3 = f11111111113;
                    f4 = f11111111114;
                    flingBehavior3 = flingBehavior111113;
                    z5 = z11111111114;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i21111111111111111111111111111114 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111111 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111111111111111111111111111114);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111111 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111111);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller111111;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope111111 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111111 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111111111 = composerStartRestartGroup.consume(localGraphicsContext111111);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21111111111111111111111111111115 = i22 >> 6;
                    int i21111111111111111111111111111116 = (i21111111111111111111111111111115 & 896) | i21111111111111111111111111111114;
                    int i21111111111111111111111111111117 = i21111111111111111111111111111115 & 7168;
                    int i21111111111111111111111111111118 = i22 >> 9;
                    int i21111111111111111111111111111119 = i21111111111111111111111111111116 | i21111111111111111111111111111117 | ((i22 << 9) & 57344) | (i21111111111111111111111111111118 & 458752) | (i21111111111111111111111111111118 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z11111111116 = z3;
                    int i31111111111111110 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111111 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111111, paddingValues3, z11111111116, orientation, f, f2, coroutineScope111111, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111111111, composerStartRestartGroup, i21111111111111111111111111111119);
                    PaddingValues paddingValues111115 = paddingValues3;
                    float f11111111115 = f;
                    float f11111111116 = f2;
                    int i31111111111111111 = i31111111111111110 >> 12;
                    boolean z11111111117 = z2;
                    Modifier modifierLazyLayoutSemantics111111 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111111, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111111116, composerStartRestartGroup, (i31111111111111111 & 112) | i21111111111111111111111111111114), orientation, z11111111117, z11111111116, composerStartRestartGroup, ((i31111111111111110 << 6) & 7168) | (i21111111111111111111111111111118 & 57344) | (i31111111111111110 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111111 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111111111111111111111111111114);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo111111 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111111 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111111112 = composerStartRestartGroup.consume(localLayoutDirection111111);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i31111111111111112 = i31111111111111110 >> 3;
                    FlingBehavior flingBehavior111114 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111111, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111111, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111111, beyondBoundsInfo111111, z11111111116, (LayoutDirection) objConsume11111111112, orientation, z11111111117, composerStartRestartGroup, (3670016 & i31111111111111112) | (MutableVector.$stable << 6) | i21111111111111111111111111111117 | ((i31111111111111110 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111111117, z11111111116, flingBehavior111114, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111111110 << 3) & 1008) | (i31111111111111111 & 7168) | (i31111111111111112 & 57344) | (i31111111111111112 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111111, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z11111111117;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues111115;
                    f3 = f11111111115;
                    f4 = f11111111116;
                    flingBehavior3 = flingBehavior111114;
                    z5 = z11111111116;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                        public final void invoke(Composer composer3, int i31111111111111113) {
                            LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= 100663296;
            i16 = i3 & 512;
            if (i16 != 0) {
                if ((i & BaseAdapter.HEADER) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i17 = 536870912;
                    } else {
                        i17 = 268435456;
                    }
                    i4 |= i17;
                }
                if ((i3 & 1024) != 0) {
                    i18 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i19 = 4;
                    } else {
                        i19 = 2;
                    }
                    i18 = i2 | i19;
                } else {
                    i18 = i2;
                }
                i20 = i4;
                if ((i20 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i211111111111111111111111111111110 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111112 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111111111111110);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111112 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111112);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller111112;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope111112 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111112 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111111113 = composerStartRestartGroup.consume(localGraphicsContext111112);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211111111111111111111111111111111 = i22 >> 6;
                    int i211111111111111111111111111111112 = (i211111111111111111111111111111111 & 896) | i211111111111111111111111111111110;
                    int i211111111111111111111111111111113 = i211111111111111111111111111111111 & 7168;
                    int i211111111111111111111111111111114 = i22 >> 9;
                    int i211111111111111111111111111111115 = i211111111111111111111111111111112 | i211111111111111111111111111111113 | ((i22 << 9) & 57344) | (i211111111111111111111111111111114 & 458752) | (i211111111111111111111111111111114 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z11111111118 = z3;
                    int i31111111111111113 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111112 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111112, paddingValues3, z11111111118, orientation, f, f2, coroutineScope111112, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111111113, composerStartRestartGroup, i211111111111111111111111111111115);
                    PaddingValues paddingValues111116 = paddingValues3;
                    float f11111111117 = f;
                    float f11111111118 = f2;
                    int i31111111111111114 = i31111111111111113 >> 12;
                    boolean z11111111119 = z2;
                    Modifier modifierLazyLayoutSemantics111112 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111112, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111111118, composerStartRestartGroup, (i31111111111111114 & 112) | i211111111111111111111111111111110), orientation, z11111111119, z11111111118, composerStartRestartGroup, ((i31111111111111113 << 6) & 7168) | (i211111111111111111111111111111114 & 57344) | (i31111111111111113 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111112 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111111111111110);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo111112 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111112 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111111114 = composerStartRestartGroup.consume(localLayoutDirection111112);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i31111111111111115 = i31111111111111113 >> 3;
                    FlingBehavior flingBehavior111115 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111112, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111112, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111112, beyondBoundsInfo111112, z11111111118, (LayoutDirection) objConsume11111111114, orientation, z11111111119, composerStartRestartGroup, (3670016 & i31111111111111115) | (MutableVector.$stable << 6) | i211111111111111111111111111111113 | ((i31111111111111113 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111111119, z11111111118, flingBehavior111115, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111111113 << 3) & 1008) | (i31111111111111114 & 7168) | (i31111111111111115 & 57344) | (i31111111111111115 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111112, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z11111111119;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues111116;
                    f3 = f11111111117;
                    f4 = f11111111118;
                    flingBehavior3 = flingBehavior111115;
                    z5 = z11111111118;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i211111111111111111111111111111116 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111113 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111111111111116);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111113 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111113);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller111113;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope111113 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111113 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111111115 = composerStartRestartGroup.consume(localGraphicsContext111113);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211111111111111111111111111111117 = i22 >> 6;
                    int i211111111111111111111111111111118 = (i211111111111111111111111111111117 & 896) | i211111111111111111111111111111116;
                    int i211111111111111111111111111111119 = i211111111111111111111111111111117 & 7168;
                    int i2111111111111111111111111111111110 = i22 >> 9;
                    int i2111111111111111111111111111111111 = i211111111111111111111111111111118 | i211111111111111111111111111111119 | ((i22 << 9) & 57344) | (i2111111111111111111111111111111110 & 458752) | (i2111111111111111111111111111111110 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z111111111110 = z3;
                    int i31111111111111116 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111113 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111113, paddingValues3, z111111111110, orientation, f, f2, coroutineScope111113, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111111115, composerStartRestartGroup, i2111111111111111111111111111111111);
                    PaddingValues paddingValues111117 = paddingValues3;
                    float f11111111119 = f;
                    float f111111111110 = f2;
                    int i31111111111111117 = i31111111111111116 >> 12;
                    boolean z111111111111 = z2;
                    Modifier modifierLazyLayoutSemantics111113 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111113, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111111111110, composerStartRestartGroup, (i31111111111111117 & 112) | i211111111111111111111111111111116), orientation, z111111111111, z111111111110, composerStartRestartGroup, ((i31111111111111116 << 6) & 7168) | (i2111111111111111111111111111111110 & 57344) | (i31111111111111116 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111113 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111111111111116);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo111113 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111113 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11111111116 = composerStartRestartGroup.consume(localLayoutDirection111113);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i31111111111111118 = i31111111111111116 >> 3;
                    FlingBehavior flingBehavior111116 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111113, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111113, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111113, beyondBoundsInfo111113, z111111111110, (LayoutDirection) objConsume11111111116, orientation, z111111111111, composerStartRestartGroup, (3670016 & i31111111111111118) | (MutableVector.$stable << 6) | i211111111111111111111111111111119 | ((i31111111111111116 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111111111, z111111111110, flingBehavior111116, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111111116 << 3) & 1008) | (i31111111111111117 & 7168) | (i31111111111111118 & 57344) | (i31111111111111118 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111113, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z111111111111;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues111117;
                    f3 = f11111111119;
                    f4 = f111111111110;
                    flingBehavior3 = flingBehavior111116;
                    z5 = z111111111110;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                        public final void invoke(Composer composer3, int i31111111111111119) {
                            LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= BaseAdapter.HEADER;
            if ((i3 & 1024) != 0) {
                i18 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i19 = 4;
                } else {
                    i19 = 2;
                }
                i18 = i2 | i19;
            } else {
                i18 = i2;
            }
            i20 = i4;
            if ((i20 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                } else {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                }
                int i2111111111111111111111111111111112 = i22 & 14;
                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111114 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111111111111111112);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111114 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111114);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller111114;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CoroutineScope coroutineScope111114 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111114 = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume11111111117 = composerStartRestartGroup.consume(localGraphicsContext111114);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2111111111111111111111111111111113 = i22 >> 6;
                int i2111111111111111111111111111111114 = (i2111111111111111111111111111111113 & 896) | i2111111111111111111111111111111112;
                int i2111111111111111111111111111111115 = i2111111111111111111111111111111113 & 7168;
                int i2111111111111111111111111111111116 = i22 >> 9;
                int i2111111111111111111111111111111117 = i2111111111111111111111111111111114 | i2111111111111111111111111111111115 | ((i22 << 9) & 57344) | (i2111111111111111111111111111111116 & 458752) | (i2111111111111111111111111111111116 & 3670016) | ((i22 << 18) & 234881024);
                boolean z111111111112 = z3;
                int i31111111111111119 = i22;
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111114 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111114, paddingValues3, z111111111112, orientation, f, f2, coroutineScope111114, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111111117, composerStartRestartGroup, i2111111111111111111111111111111117);
                PaddingValues paddingValues111118 = paddingValues3;
                float f111111111111 = f;
                float f111111111112 = f2;
                int i311111111111111110 = i31111111111111119 >> 12;
                boolean z111111111113 = z2;
                Modifier modifierLazyLayoutSemantics111114 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111114, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111111111112, composerStartRestartGroup, (i311111111111111110 & 112) | i2111111111111111111111111111111112), orientation, z111111111113, z111111111112, composerStartRestartGroup, ((i31111111111111119 << 6) & 7168) | (i2111111111111111111111111111111116 & 57344) | (i31111111111111119 & 458752));
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111114 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111111111111111112);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo111114 = lazyStaggeredGridState.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111114 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume11111111118 = composerStartRestartGroup.consume(localLayoutDirection111114);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i311111111111111111 = i31111111111111119 >> 3;
                FlingBehavior flingBehavior111117 = flingBehavior;
                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111114, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111114, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111114, beyondBoundsInfo111114, z111111111112, (LayoutDirection) objConsume11111111118, orientation, z111111111113, composerStartRestartGroup, (3670016 & i311111111111111111) | (MutableVector.$stable << 6) | i2111111111111111111111111111111115 | ((i31111111111111119 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111111113, z111111111112, flingBehavior111117, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111111119 << 3) & 1008) | (i311111111111111110 & 7168) | (i311111111111111111 & 57344) | (i311111111111111111 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111114, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z111111111113;
                composer2 = composerStartRestartGroup;
                modifier4 = modifier3;
                paddingValues4 = paddingValues111118;
                f3 = f111111111111;
                f4 = f111111111112;
                flingBehavior3 = flingBehavior111117;
                z5 = z111111111112;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                } else {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                }
                int i2111111111111111111111111111111118 = i22 & 14;
                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111115 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111111111111111118);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111115 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111115);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller111115;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CoroutineScope coroutineScope111115 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111115 = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume11111111119 = composerStartRestartGroup.consume(localGraphicsContext111115);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2111111111111111111111111111111119 = i22 >> 6;
                int i21111111111111111111111111111111110 = (i2111111111111111111111111111111119 & 896) | i2111111111111111111111111111111118;
                int i21111111111111111111111111111111111 = i2111111111111111111111111111111119 & 7168;
                int i21111111111111111111111111111111112 = i22 >> 9;
                int i21111111111111111111111111111111113 = i21111111111111111111111111111111110 | i21111111111111111111111111111111111 | ((i22 << 9) & 57344) | (i21111111111111111111111111111111112 & 458752) | (i21111111111111111111111111111111112 & 3670016) | ((i22 << 18) & 234881024);
                boolean z111111111114 = z3;
                int i311111111111111112 = i22;
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111115 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111115, paddingValues3, z111111111114, orientation, f, f2, coroutineScope111115, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume11111111119, composerStartRestartGroup, i21111111111111111111111111111111113);
                PaddingValues paddingValues111119 = paddingValues3;
                float f111111111113 = f;
                float f111111111114 = f2;
                int i311111111111111113 = i311111111111111112 >> 12;
                boolean z111111111115 = z2;
                Modifier modifierLazyLayoutSemantics111115 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111115, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111111111114, composerStartRestartGroup, (i311111111111111113 & 112) | i2111111111111111111111111111111118), orientation, z111111111115, z111111111114, composerStartRestartGroup, ((i311111111111111112 << 6) & 7168) | (i21111111111111111111111111111111112 & 57344) | (i311111111111111112 & 458752));
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111115 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111111111111111118);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo111115 = lazyStaggeredGridState.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111115 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume111111111110 = composerStartRestartGroup.consume(localLayoutDirection111115);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i311111111111111114 = i311111111111111112 >> 3;
                FlingBehavior flingBehavior111118 = flingBehavior;
                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111115, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111115, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111115, beyondBoundsInfo111115, z111111111114, (LayoutDirection) objConsume111111111110, orientation, z111111111115, composerStartRestartGroup, (3670016 & i311111111111111114) | (MutableVector.$stable << 6) | i21111111111111111111111111111111111 | ((i311111111111111112 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111111115, z111111111114, flingBehavior111118, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111111111111112 << 3) & 1008) | (i311111111111111113 & 7168) | (i311111111111111114 & 57344) | (i311111111111111114 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111115, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z111111111115;
                composer2 = composerStartRestartGroup;
                modifier4 = modifier3;
                paddingValues4 = paddingValues111119;
                f3 = f111111111113;
                f4 = f111111111114;
                flingBehavior3 = flingBehavior111118;
                z5 = z111111111114;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                    public final void invoke(Composer composer3, int i311111111111111115) {
                        LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i4 |= 24576;
        paddingValues2 = paddingValues;
        i10 = i3 & 32;
        if (i10 != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z3 = z;
        } else {
            z3 = z;
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(z3)) {
                    i11 = 131072;
                } else {
                    i11 = 65536;
                }
                i4 |= i11;
            }
        }
        if ((i & 1572864) != 0) {
            if ((i3 & 64) == 0) {
                i23 = 524288;
            } else {
                i23 = 524288;
            }
            i4 |= i23;
        }
        i12 = i3 & 128;
        if (i12 != 0) {
            i4 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(z2)) {
                i13 = 8388608;
            } else {
                i13 = 4194304;
            }
            i4 |= i13;
        }
        i14 = i3 & 256;
        if (i14 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(f)) {
                    i15 = 67108864;
                } else {
                    i15 = 33554432;
                }
                i4 |= i15;
            }
            i16 = i3 & 512;
            if (i16 != 0) {
                if ((i & BaseAdapter.HEADER) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i17 = 536870912;
                    } else {
                        i17 = 268435456;
                    }
                    i4 |= i17;
                }
                if ((i3 & 1024) != 0) {
                    i18 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i19 = 4;
                    } else {
                        i19 = 2;
                    }
                    i18 = i2 | i19;
                } else {
                    i18 = i2;
                }
                i20 = i4;
                if ((i20 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i21111111111111111111111111111111114 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111116 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111111111111111111111111111111114);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111116 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111116);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller111116;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope111116 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111116 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111111111 = composerStartRestartGroup.consume(localGraphicsContext111116);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i21111111111111111111111111111111115 = i22 >> 6;
                    int i21111111111111111111111111111111116 = (i21111111111111111111111111111111115 & 896) | i21111111111111111111111111111111114;
                    int i21111111111111111111111111111111117 = i21111111111111111111111111111111115 & 7168;
                    int i21111111111111111111111111111111118 = i22 >> 9;
                    int i21111111111111111111111111111111119 = i21111111111111111111111111111111116 | i21111111111111111111111111111111117 | ((i22 << 9) & 57344) | (i21111111111111111111111111111111118 & 458752) | (i21111111111111111111111111111111118 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z111111111116 = z3;
                    int i311111111111111115 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111116 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111116, paddingValues3, z111111111116, orientation, f, f2, coroutineScope111116, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111111111, composerStartRestartGroup, i21111111111111111111111111111111119);
                    PaddingValues paddingValues1111110 = paddingValues3;
                    float f111111111115 = f;
                    float f111111111116 = f2;
                    int i311111111111111116 = i311111111111111115 >> 12;
                    boolean z111111111117 = z2;
                    Modifier modifierLazyLayoutSemantics111116 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111116, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111111111116, composerStartRestartGroup, (i311111111111111116 & 112) | i21111111111111111111111111111111114), orientation, z111111111117, z111111111116, composerStartRestartGroup, ((i311111111111111115 << 6) & 7168) | (i21111111111111111111111111111111118 & 57344) | (i311111111111111115 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111116 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111111111111111111111111111111114);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo111116 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111116 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111111112 = composerStartRestartGroup.consume(localLayoutDirection111116);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i311111111111111117 = i311111111111111115 >> 3;
                    FlingBehavior flingBehavior111119 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111116, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111116, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111116, beyondBoundsInfo111116, z111111111116, (LayoutDirection) objConsume111111111112, orientation, z111111111117, composerStartRestartGroup, (3670016 & i311111111111111117) | (MutableVector.$stable << 6) | i21111111111111111111111111111111117 | ((i311111111111111115 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111111117, z111111111116, flingBehavior111119, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111111111111115 << 3) & 1008) | (i311111111111111116 & 7168) | (i311111111111111117 & 57344) | (i311111111111111117 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111116, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z111111111117;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues1111110;
                    f3 = f111111111115;
                    f4 = f111111111116;
                    flingBehavior3 = flingBehavior111119;
                    z5 = z111111111116;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    } else {
                        if (i6 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i8 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i10 != 0) {
                            z3 = false;
                        }
                        if ((i3 & 64) != 0) {
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i21 = i20 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            i21 = i20;
                        }
                        if (i12 != 0) {
                            z2 = true;
                        } else {
                            z2 = z2;
                        }
                        if (i14 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f;
                        }
                        if (i16 != 0) {
                            f2 = Dp.m6890constructorimpl(0.0f);
                        } else {
                            f2 = f2;
                        }
                        flingBehavior = flingBehavior2;
                        i22 = i21;
                        f = fM6890constructorimpl;
                        modifier3 = companion;
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                    }
                    int i211111111111111111111111111111111110 = i22 & 14;
                    Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111117 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111111111111111110);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111117 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111117);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller111117;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CoroutineScope coroutineScope111117 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111117 = CompositionLocalsKt.getLocalGraphicsContext();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111111113 = composerStartRestartGroup.consume(localGraphicsContext111117);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i211111111111111111111111111111111111 = i22 >> 6;
                    int i211111111111111111111111111111111112 = (i211111111111111111111111111111111111 & 896) | i211111111111111111111111111111111110;
                    int i211111111111111111111111111111111113 = i211111111111111111111111111111111111 & 7168;
                    int i211111111111111111111111111111111114 = i22 >> 9;
                    int i211111111111111111111111111111111115 = i211111111111111111111111111111111112 | i211111111111111111111111111111111113 | ((i22 << 9) & 57344) | (i211111111111111111111111111111111114 & 458752) | (i211111111111111111111111111111111114 & 3670016) | ((i22 << 18) & 234881024);
                    boolean z111111111118 = z3;
                    int i311111111111111118 = i22;
                    Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111117 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111117, paddingValues3, z111111111118, orientation, f, f2, coroutineScope111117, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111111113, composerStartRestartGroup, i211111111111111111111111111111111115);
                    PaddingValues paddingValues1111111 = paddingValues3;
                    float f111111111117 = f;
                    float f111111111118 = f2;
                    int i311111111111111119 = i311111111111111118 >> 12;
                    boolean z111111111119 = z2;
                    Modifier modifierLazyLayoutSemantics111117 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111117, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z111111111118, composerStartRestartGroup, (i311111111111111119 & 112) | i211111111111111111111111111111111110), orientation, z111111111119, z111111111118, composerStartRestartGroup, ((i311111111111111118 << 6) & 7168) | (i211111111111111111111111111111111114 & 57344) | (i311111111111111118 & 458752));
                    LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111117 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111111111111111110);
                    LazyLayoutBeyondBoundsInfo beyondBoundsInfo111117 = lazyStaggeredGridState.getBeyondBoundsInfo();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111117 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111111111114 = composerStartRestartGroup.consume(localLayoutDirection111117);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i3111111111111111110 = i311111111111111118 >> 3;
                    FlingBehavior flingBehavior1111110 = flingBehavior;
                    LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111117, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111117, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111117, beyondBoundsInfo111117, z111111111118, (LayoutDirection) objConsume111111111114, orientation, z111111111119, composerStartRestartGroup, (3670016 & i3111111111111111110) | (MutableVector.$stable << 6) | i211111111111111111111111111111111113 | ((i311111111111111118 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z111111111119, z111111111118, flingBehavior1111110, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i311111111111111118 << 3) & 1008) | (i311111111111111119 & 7168) | (i3111111111111111110 & 57344) | (i3111111111111111110 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111117, composerStartRestartGroup, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z111111111119;
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier3;
                    paddingValues4 = paddingValues1111111;
                    f3 = f111111111117;
                    f4 = f111111111118;
                    flingBehavior3 = flingBehavior1111110;
                    z5 = z111111111118;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                        public final void invoke(Composer composer3, int i3111111111111111111) {
                            LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= BaseAdapter.HEADER;
            if ((i3 & 1024) != 0) {
                i18 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i19 = 4;
                } else {
                    i19 = 2;
                }
                i18 = i2 | i19;
            } else {
                i18 = i2;
            }
            i20 = i4;
            if ((i20 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                } else {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                }
                int i211111111111111111111111111111111116 = i22 & 14;
                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111118 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111111111111111116);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111118 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111118);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller111118;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CoroutineScope coroutineScope111118 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111118 = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume111111111115 = composerStartRestartGroup.consume(localGraphicsContext111118);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i211111111111111111111111111111111117 = i22 >> 6;
                int i211111111111111111111111111111111118 = (i211111111111111111111111111111111117 & 896) | i211111111111111111111111111111111116;
                int i211111111111111111111111111111111119 = i211111111111111111111111111111111117 & 7168;
                int i2111111111111111111111111111111111110 = i22 >> 9;
                int i2111111111111111111111111111111111111 = i211111111111111111111111111111111118 | i211111111111111111111111111111111119 | ((i22 << 9) & 57344) | (i2111111111111111111111111111111111110 & 458752) | (i2111111111111111111111111111111111110 & 3670016) | ((i22 << 18) & 234881024);
                boolean z1111111111110 = z3;
                int i3111111111111111111 = i22;
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111118 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111118, paddingValues3, z1111111111110, orientation, f, f2, coroutineScope111118, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111111115, composerStartRestartGroup, i2111111111111111111111111111111111111);
                PaddingValues paddingValues1111112 = paddingValues3;
                float f111111111119 = f;
                float f1111111111110 = f2;
                int i3111111111111111112 = i3111111111111111111 >> 12;
                boolean z1111111111111 = z2;
                Modifier modifierLazyLayoutSemantics111118 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111118, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111111111110, composerStartRestartGroup, (i3111111111111111112 & 112) | i211111111111111111111111111111111116), orientation, z1111111111111, z1111111111110, composerStartRestartGroup, ((i3111111111111111111 << 6) & 7168) | (i2111111111111111111111111111111111110 & 57344) | (i3111111111111111111 & 458752));
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111118 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111111111111111116);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo111118 = lazyStaggeredGridState.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111118 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume111111111116 = composerStartRestartGroup.consume(localLayoutDirection111118);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i3111111111111111113 = i3111111111111111111 >> 3;
                FlingBehavior flingBehavior1111111 = flingBehavior;
                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111118, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111118, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111118, beyondBoundsInfo111118, z1111111111110, (LayoutDirection) objConsume111111111116, orientation, z1111111111111, composerStartRestartGroup, (3670016 & i3111111111111111113) | (MutableVector.$stable << 6) | i211111111111111111111111111111111119 | ((i3111111111111111111 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111111111, z1111111111110, flingBehavior1111111, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111111111111111 << 3) & 1008) | (i3111111111111111112 & 7168) | (i3111111111111111113 & 57344) | (i3111111111111111113 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111118, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z1111111111111;
                composer2 = composerStartRestartGroup;
                modifier4 = modifier3;
                paddingValues4 = paddingValues1111112;
                f3 = f111111111119;
                f4 = f1111111111110;
                flingBehavior3 = flingBehavior1111111;
                z5 = z1111111111110;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                } else {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                }
                int i2111111111111111111111111111111111112 = i22 & 14;
                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda111119 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111111111111111111112);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller111119 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller111119);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller111119;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CoroutineScope coroutineScope111119 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext111119 = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume111111111117 = composerStartRestartGroup.consume(localGraphicsContext111119);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2111111111111111111111111111111111113 = i22 >> 6;
                int i2111111111111111111111111111111111114 = (i2111111111111111111111111111111111113 & 896) | i2111111111111111111111111111111111112;
                int i2111111111111111111111111111111111115 = i2111111111111111111111111111111111113 & 7168;
                int i2111111111111111111111111111111111116 = i22 >> 9;
                int i2111111111111111111111111111111111117 = i2111111111111111111111111111111111114 | i2111111111111111111111111111111111115 | ((i22 << 9) & 57344) | (i2111111111111111111111111111111111116 & 458752) | (i2111111111111111111111111111111111116 & 3670016) | ((i22 << 18) & 234881024);
                boolean z1111111111112 = z3;
                int i3111111111111111114 = i22;
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111119 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda111119, paddingValues3, z1111111111112, orientation, f, f2, coroutineScope111119, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111111117, composerStartRestartGroup, i2111111111111111111111111111111111117);
                PaddingValues paddingValues1111113 = paddingValues3;
                float f1111111111111 = f;
                float f1111111111112 = f2;
                int i3111111111111111115 = i3111111111111111114 >> 12;
                boolean z1111111111113 = z2;
                Modifier modifierLazyLayoutSemantics111119 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda111119, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111111111112, composerStartRestartGroup, (i3111111111111111115 & 112) | i2111111111111111111111111111111111112), orientation, z1111111111113, z1111111111112, composerStartRestartGroup, ((i3111111111111111114 << 6) & 7168) | (i2111111111111111111111111111111111116 & 57344) | (i3111111111111111114 & 458752));
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111119 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111111111111111111112);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo111119 = lazyStaggeredGridState.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection111119 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume111111111118 = composerStartRestartGroup.consume(localLayoutDirection111119);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i3111111111111111116 = i3111111111111111114 >> 3;
                FlingBehavior flingBehavior1111112 = flingBehavior;
                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda111119, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics111119, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState111119, beyondBoundsInfo111119, z1111111111112, (LayoutDirection) objConsume111111111118, orientation, z1111111111113, composerStartRestartGroup, (3670016 & i3111111111111111116) | (MutableVector.$stable << 6) | i2111111111111111111111111111111111115 | ((i3111111111111111114 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111111113, z1111111111112, flingBehavior1111112, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111111111111114 << 3) & 1008) | (i3111111111111111115 & 7168) | (i3111111111111111116 & 57344) | (i3111111111111111116 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE111119, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z1111111111113;
                composer2 = composerStartRestartGroup;
                modifier4 = modifier3;
                paddingValues4 = paddingValues1111113;
                f3 = f1111111111111;
                f4 = f1111111111112;
                flingBehavior3 = flingBehavior1111112;
                z5 = z1111111111112;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                    public final void invoke(Composer composer3, int i3111111111111111117) {
                        LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i4 |= 100663296;
        i16 = i3 & 512;
        if (i16 != 0) {
            if ((i & BaseAdapter.HEADER) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i17 = 536870912;
                } else {
                    i17 = 268435456;
                }
                i4 |= i17;
            }
            if ((i3 & 1024) != 0) {
                i18 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i19 = 4;
                } else {
                    i19 = 2;
                }
                i18 = i2 | i19;
            } else {
                i18 = i2;
            }
            i20 = i4;
            if ((i20 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                } else {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                }
                int i2111111111111111111111111111111111118 = i22 & 14;
                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1111110 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i2111111111111111111111111111111111118);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1111110 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1111110);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller1111110;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CoroutineScope coroutineScope1111110 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1111110 = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume111111111119 = composerStartRestartGroup.consume(localGraphicsContext1111110);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2111111111111111111111111111111111119 = i22 >> 6;
                int i21111111111111111111111111111111111110 = (i2111111111111111111111111111111111119 & 896) | i2111111111111111111111111111111111118;
                int i21111111111111111111111111111111111111 = i2111111111111111111111111111111111119 & 7168;
                int i21111111111111111111111111111111111112 = i22 >> 9;
                int i21111111111111111111111111111111111113 = i21111111111111111111111111111111111110 | i21111111111111111111111111111111111111 | ((i22 << 9) & 57344) | (i21111111111111111111111111111111111112 & 458752) | (i21111111111111111111111111111111111112 & 3670016) | ((i22 << 18) & 234881024);
                boolean z1111111111114 = z3;
                int i3111111111111111117 = i22;
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1111110 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1111110, paddingValues3, z1111111111114, orientation, f, f2, coroutineScope1111110, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume111111111119, composerStartRestartGroup, i21111111111111111111111111111111111113);
                PaddingValues paddingValues1111114 = paddingValues3;
                float f1111111111113 = f;
                float f1111111111114 = f2;
                int i3111111111111111118 = i3111111111111111117 >> 12;
                boolean z1111111111115 = z2;
                Modifier modifierLazyLayoutSemantics1111110 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1111110, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111111111114, composerStartRestartGroup, (i3111111111111111118 & 112) | i2111111111111111111111111111111111118), orientation, z1111111111115, z1111111111114, composerStartRestartGroup, ((i3111111111111111117 << 6) & 7168) | (i21111111111111111111111111111111111112 & 57344) | (i3111111111111111117 & 458752));
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1111110 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i2111111111111111111111111111111111118);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo1111110 = lazyStaggeredGridState.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1111110 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111111111110 = composerStartRestartGroup.consume(localLayoutDirection1111110);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i3111111111111111119 = i3111111111111111117 >> 3;
                FlingBehavior flingBehavior1111113 = flingBehavior;
                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1111110, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1111110, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1111110, beyondBoundsInfo1111110, z1111111111114, (LayoutDirection) objConsume1111111111110, orientation, z1111111111115, composerStartRestartGroup, (3670016 & i3111111111111111119) | (MutableVector.$stable << 6) | i21111111111111111111111111111111111111 | ((i3111111111111111117 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111111115, z1111111111114, flingBehavior1111113, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i3111111111111111117 << 3) & 1008) | (i3111111111111111118 & 7168) | (i3111111111111111119 & 57344) | (i3111111111111111119 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1111110, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z1111111111115;
                composer2 = composerStartRestartGroup;
                modifier4 = modifier3;
                paddingValues4 = paddingValues1111114;
                f3 = f1111111111113;
                f4 = f1111111111114;
                flingBehavior3 = flingBehavior1111113;
                z5 = z1111111111114;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                } else {
                    if (i6 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i8 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i10 != 0) {
                        z3 = false;
                    }
                    if ((i3 & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i21 = i20 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        i21 = i20;
                    }
                    if (i12 != 0) {
                        z2 = true;
                    } else {
                        z2 = z2;
                    }
                    if (i14 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f;
                    }
                    if (i16 != 0) {
                        f2 = Dp.m6890constructorimpl(0.0f);
                    } else {
                        f2 = f2;
                    }
                    flingBehavior = flingBehavior2;
                    i22 = i21;
                    f = fM6890constructorimpl;
                    modifier3 = companion;
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
                }
                int i21111111111111111111111111111111111114 = i22 & 14;
                Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1111111 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i21111111111111111111111111111111111114);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1111111 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1111111);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller1111111;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CoroutineScope coroutineScope1111111 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1111111 = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111111111111 = composerStartRestartGroup.consume(localGraphicsContext1111111);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i21111111111111111111111111111111111115 = i22 >> 6;
                int i21111111111111111111111111111111111116 = (i21111111111111111111111111111111111115 & 896) | i21111111111111111111111111111111111114;
                int i21111111111111111111111111111111111117 = i21111111111111111111111111111111111115 & 7168;
                int i21111111111111111111111111111111111118 = i22 >> 9;
                int i21111111111111111111111111111111111119 = i21111111111111111111111111111111111116 | i21111111111111111111111111111111111117 | ((i22 << 9) & 57344) | (i21111111111111111111111111111111111118 & 458752) | (i21111111111111111111111111111111111118 & 3670016) | ((i22 << 18) & 234881024);
                boolean z1111111111116 = z3;
                int i31111111111111111110 = i22;
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1111111 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1111111, paddingValues3, z1111111111116, orientation, f, f2, coroutineScope1111111, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111111111111, composerStartRestartGroup, i21111111111111111111111111111111111119);
                PaddingValues paddingValues1111115 = paddingValues3;
                float f1111111111115 = f;
                float f1111111111116 = f2;
                int i31111111111111111111 = i31111111111111111110 >> 12;
                boolean z1111111111117 = z2;
                Modifier modifierLazyLayoutSemantics1111111 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1111111, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111111111116, composerStartRestartGroup, (i31111111111111111111 & 112) | i21111111111111111111111111111111111114), orientation, z1111111111117, z1111111111116, composerStartRestartGroup, ((i31111111111111111110 << 6) & 7168) | (i21111111111111111111111111111111111118 & 57344) | (i31111111111111111110 & 458752));
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1111111 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i21111111111111111111111111111111111114);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo1111111 = lazyStaggeredGridState.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1111111 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111111111112 = composerStartRestartGroup.consume(localLayoutDirection1111111);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i31111111111111111112 = i31111111111111111110 >> 3;
                FlingBehavior flingBehavior1111114 = flingBehavior;
                LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1111111, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1111111, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1111111, beyondBoundsInfo1111111, z1111111111116, (LayoutDirection) objConsume1111111111112, orientation, z1111111111117, composerStartRestartGroup, (3670016 & i31111111111111111112) | (MutableVector.$stable << 6) | i21111111111111111111111111111111111117 | ((i31111111111111111110 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111111117, z1111111111116, flingBehavior1111114, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111111111110 << 3) & 1008) | (i31111111111111111111 & 7168) | (i31111111111111111112 & 57344) | (i31111111111111111112 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1111111, composerStartRestartGroup, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z1111111111117;
                composer2 = composerStartRestartGroup;
                modifier4 = modifier3;
                paddingValues4 = paddingValues1111115;
                f3 = f1111111111115;
                f4 = f1111111111116;
                flingBehavior3 = flingBehavior1111114;
                z5 = z1111111111116;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                    public final void invoke(Composer composer3, int i31111111111111111113) {
                        LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i4 |= BaseAdapter.HEADER;
        if ((i3 & 1024) != 0) {
            i18 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i19 = 4;
            } else {
                i19 = 2;
            }
            i18 = i2 | i19;
        } else {
            i18 = i2;
        }
        i20 = i4;
        if ((i20 & 306783379) == 306783378) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i6 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i8 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                } else {
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
                if (i10 != 0) {
                    z3 = false;
                }
                if ((i3 & 64) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i21 = i20 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    i21 = i20;
                }
                if (i12 != 0) {
                    z2 = true;
                } else {
                    z2 = z2;
                }
                if (i14 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f;
                }
                if (i16 != 0) {
                    f2 = Dp.m6890constructorimpl(0.0f);
                } else {
                    f2 = f2;
                }
                flingBehavior = flingBehavior2;
                i22 = i21;
                f = fM6890constructorimpl;
                modifier3 = companion;
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
            } else {
                if (i6 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i8 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                } else {
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
                if (i10 != 0) {
                    z3 = false;
                }
                if ((i3 & 64) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i21 = i20 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    i21 = i20;
                }
                if (i12 != 0) {
                    z2 = true;
                } else {
                    z2 = z2;
                }
                if (i14 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f;
                }
                if (i16 != 0) {
                    f2 = Dp.m6890constructorimpl(0.0f);
                } else {
                    f2 = f2;
                }
                flingBehavior = flingBehavior2;
                i22 = i21;
                f = fM6890constructorimpl;
                modifier3 = companion;
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
            }
            int i211111111111111111111111111111111111110 = i22 & 14;
            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1111112 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111111111111111111110);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1111112 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1111112);
                objRememberedValue = compositionScopedCoroutineScopeCanceller1111112;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CoroutineScope coroutineScope1111112 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1111112 = CompositionLocalsKt.getLocalGraphicsContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume1111111111113 = composerStartRestartGroup.consume(localGraphicsContext1111112);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i211111111111111111111111111111111111111 = i22 >> 6;
            int i211111111111111111111111111111111111112 = (i211111111111111111111111111111111111111 & 896) | i211111111111111111111111111111111111110;
            int i211111111111111111111111111111111111113 = i211111111111111111111111111111111111111 & 7168;
            int i211111111111111111111111111111111111114 = i22 >> 9;
            int i211111111111111111111111111111111111115 = i211111111111111111111111111111111111112 | i211111111111111111111111111111111111113 | ((i22 << 9) & 57344) | (i211111111111111111111111111111111111114 & 458752) | (i211111111111111111111111111111111111114 & 3670016) | ((i22 << 18) & 234881024);
            boolean z1111111111118 = z3;
            int i31111111111111111113 = i22;
            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1111112 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1111112, paddingValues3, z1111111111118, orientation, f, f2, coroutineScope1111112, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111111111113, composerStartRestartGroup, i211111111111111111111111111111111111115);
            PaddingValues paddingValues1111116 = paddingValues3;
            float f1111111111117 = f;
            float f1111111111118 = f2;
            int i31111111111111111114 = i31111111111111111113 >> 12;
            boolean z1111111111119 = z2;
            Modifier modifierLazyLayoutSemantics1111112 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1111112, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z1111111111118, composerStartRestartGroup, (i31111111111111111114 & 112) | i211111111111111111111111111111111111110), orientation, z1111111111119, z1111111111118, composerStartRestartGroup, ((i31111111111111111113 << 6) & 7168) | (i211111111111111111111111111111111111114 & 57344) | (i31111111111111111113 & 458752));
            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1111112 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111111111111111111110);
            LazyLayoutBeyondBoundsInfo beyondBoundsInfo1111112 = lazyStaggeredGridState.getBeyondBoundsInfo();
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1111112 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume1111111111114 = composerStartRestartGroup.consume(localLayoutDirection1111112);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i31111111111111111115 = i31111111111111111113 >> 3;
            FlingBehavior flingBehavior1111115 = flingBehavior;
            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1111112, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1111112, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1111112, beyondBoundsInfo1111112, z1111111111118, (LayoutDirection) objConsume1111111111114, orientation, z1111111111119, composerStartRestartGroup, (3670016 & i31111111111111111115) | (MutableVector.$stable << 6) | i211111111111111111111111111111111111113 | ((i31111111111111111113 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z1111111111119, z1111111111118, flingBehavior1111115, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111111111113 << 3) & 1008) | (i31111111111111111114 & 7168) | (i31111111111111111115 & 57344) | (i31111111111111111115 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1111112, composerStartRestartGroup, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z4 = z1111111111119;
            composer2 = composerStartRestartGroup;
            modifier4 = modifier3;
            paddingValues4 = paddingValues1111116;
            f3 = f1111111111117;
            f4 = f1111111111118;
            flingBehavior3 = flingBehavior1111115;
            z5 = z1111111111118;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i6 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i8 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                } else {
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
                if (i10 != 0) {
                    z3 = false;
                }
                if ((i3 & 64) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i21 = i20 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    i21 = i20;
                }
                if (i12 != 0) {
                    z2 = true;
                } else {
                    z2 = z2;
                }
                if (i14 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f;
                }
                if (i16 != 0) {
                    f2 = Dp.m6890constructorimpl(0.0f);
                } else {
                    f2 = f2;
                }
                flingBehavior = flingBehavior2;
                i22 = i21;
                f = fM6890constructorimpl;
                modifier3 = companion;
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
            } else {
                if (i6 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i8 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                } else {
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
                if (i10 != 0) {
                    z3 = false;
                }
                if ((i3 & 64) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i21 = i20 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    i21 = i20;
                }
                if (i12 != 0) {
                    z2 = true;
                } else {
                    z2 = z2;
                }
                if (i14 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f;
                }
                if (i16 != 0) {
                    f2 = Dp.m6890constructorimpl(0.0f);
                } else {
                    f2 = f2;
                }
                flingBehavior = flingBehavior2;
                i22 = i21;
                f = fM6890constructorimpl;
                modifier3 = companion;
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(288295126, i22, i18, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
            }
            int i211111111111111111111111111111111111116 = i22 & 14;
            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda1111113 = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, function1, composerStartRestartGroup, ((i18 << 3) & 112) | i211111111111111111111111111111111111116);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller1111113 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller1111113);
                objRememberedValue = compositionScopedCoroutineScopeCanceller1111113;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CoroutineScope coroutineScope1111113 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext1111113 = CompositionLocalsKt.getLocalGraphicsContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume1111111111115 = composerStartRestartGroup.consume(localGraphicsContext1111113);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i211111111111111111111111111111111111117 = i22 >> 6;
            int i211111111111111111111111111111111111118 = (i211111111111111111111111111111111111117 & 896) | i211111111111111111111111111111111111116;
            int i211111111111111111111111111111111111119 = i211111111111111111111111111111111111117 & 7168;
            int i2111111111111111111111111111111111111110 = i22 >> 9;
            int i2111111111111111111111111111111111111111 = i211111111111111111111111111111111111118 | i211111111111111111111111111111111111119 | ((i22 << 9) & 57344) | (i2111111111111111111111111111111111111110 & 458752) | (i2111111111111111111111111111111111111110 & 3670016) | ((i22 << 18) & 234881024);
            boolean z11111111111110 = z3;
            int i31111111111111111116 = i22;
            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1111113 = LazyStaggeredGridMeasurePolicyKt.m939rememberStaggeredGridMeasurePolicyqKj4JfE(lazyStaggeredGridState, function0RememberStaggeredGridItemProviderLambda1111113, paddingValues3, z11111111111110, orientation, f, f2, coroutineScope1111113, lazyGridStaggeredGridSlotsProvider, (GraphicsContext) objConsume1111111111115, composerStartRestartGroup, i2111111111111111111111111111111111111111);
            PaddingValues paddingValues1111117 = paddingValues3;
            float f1111111111119 = f;
            float f11111111111110 = f2;
            int i31111111111111111117 = i31111111111111111116 >> 12;
            boolean z11111111111111 = z2;
            Modifier modifierLazyLayoutSemantics1111113 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda1111113, LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z11111111111110, composerStartRestartGroup, (i31111111111111111117 & 112) | i211111111111111111111111111111111111116), orientation, z11111111111111, z11111111111110, composerStartRestartGroup, ((i31111111111111111116 << 6) & 7168) | (i2111111111111111111111111111111111111110 & 57344) | (i31111111111111111116 & 458752));
            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1111113 = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, i211111111111111111111111111111111111116);
            LazyLayoutBeyondBoundsInfo beyondBoundsInfo1111113 = lazyStaggeredGridState.getBeyondBoundsInfo();
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection1111113 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume1111111111116 = composerStartRestartGroup.consume(localLayoutDirection1111113);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i31111111111111111118 = i31111111111111111116 >> 3;
            FlingBehavior flingBehavior1111116 = flingBehavior;
            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda1111113, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics1111113, lazyLayoutBeyondBoundsStateRememberLazyStaggeredGridBeyondBoundsState1111113, beyondBoundsInfo1111113, z11111111111110, (LayoutDirection) objConsume1111111111116, orientation, z11111111111111, composerStartRestartGroup, (3670016 & i31111111111111111118) | (MutableVector.$stable << 6) | i211111111111111111111111111111111111119 | ((i31111111111111111116 << 12) & 458752)).then(lazyStaggeredGridState.getItemAnimator$foundation_release().getModifier()), lazyStaggeredGridState, orientation, z11111111111111, z11111111111110, flingBehavior1111116, lazyStaggeredGridState.getMutableInteractionSource(), null, composerStartRestartGroup, ((i31111111111111111116 << 3) & 1008) | (i31111111111111111117 & 7168) | (i31111111111111111118 & 57344) | (i31111111111111111118 & 458752), 64), lazyStaggeredGridState.getPrefetchState(), function2M939rememberStaggeredGridMeasurePolicyqKj4JfE1111113, composerStartRestartGroup, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z4 = z11111111111111;
            composer2 = composerStartRestartGroup;
            modifier4 = modifier3;
            paddingValues4 = paddingValues1111117;
            f3 = f1111111111119;
            f4 = f11111111111110;
            flingBehavior3 = flingBehavior1111116;
            z5 = z11111111111110;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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

                public final void invoke(Composer composer3, int i31111111111111111119) {
                    LazyStaggeredGridKt.m928LazyStaggeredGridLJWHXA8(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier4, paddingValues4, z5, flingBehavior3, z4, f3, f4, function1, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                }
            });
        }
    }
}
