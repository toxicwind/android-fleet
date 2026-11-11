package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGridMeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u007f\u0010\u0000\u001a\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\u0002\b\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a)\u0010\u001c\u001a\u00020\u0012*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\u001f\u001a)\u0010 \u001a\u00020\u0012*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\u001f\u001a!\u0010!\u001a\u00020\u0012*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\"\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"rememberStaggeredGridMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "Lkotlin/ExtensionFunctionType;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacing", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "slots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "rememberStaggeredGridMeasurePolicy-qKj4JfE", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;FFLkotlinx/coroutines/CoroutineScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "afterPadding", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/ui/unit/LayoutDirection;)F", "beforePadding", "startPadding", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/unit/LayoutDirection;)F", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridMeasurePolicyKt {

    /* JADX INFO: compiled from: LazyStaggeredGridMeasurePolicy.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0087 A[PHI: r3
      0x0087: PHI (r3v23 androidx.compose.foundation.gestures.Orientation) = (r3v21 androidx.compose.foundation.gestures.Orientation), (r3v24 androidx.compose.foundation.gestures.Orientation) binds: [B:36:0x0085, B:32:0x007e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:47:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:48:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:59:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:62:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:65:0x00d9 A[PHI: r11
      0x00d9: PHI (r11v18 androidx.compose.foundation.lazy.staggeredgrid.LazyGridStaggeredGridSlotsProvider) = 
      (r11v15 androidx.compose.foundation.lazy.staggeredgrid.LazyGridStaggeredGridSlotsProvider)
      (r11v19 androidx.compose.foundation.lazy.staggeredgrid.LazyGridStaggeredGridSlotsProvider)
     binds: [B:64:0x00d7, B:60:0x00d1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:68:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:70:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:73:0x010e  */
    /* JADX INFO: renamed from: rememberStaggeredGridMeasurePolicy-qKj4JfE, reason: not valid java name */
    public static final Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> m939rememberStaggeredGridMeasurePolicyqKj4JfE(final LazyStaggeredGridState lazyStaggeredGridState, final Function0<? extends LazyStaggeredGridItemProvider> function0, final PaddingValues paddingValues, final boolean z, Orientation orientation, final float f, float f2, final CoroutineScope coroutineScope, LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider, final GraphicsContext graphicsContext, Composer composer, int i) {
        Orientation orientation2;
        boolean z2;
        boolean z3;
        LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider2;
        boolean zChanged;
        Object objRememberedValue;
        ComposerKt.sourceInformationMarkerStart(composer, 1630140849, "C(rememberStaggeredGridMeasurePolicy)P(9,4!1,7,6,5:c#ui.unit.Dp,2:c#ui.unit.Dp!1,8)50@2173L2923:LazyStaggeredGridMeasurePolicy.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1630140849, i, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberStaggeredGridMeasurePolicy (LazyStaggeredGridMeasurePolicy.kt:50)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1650675918, "CC(remember):LazyStaggeredGridMeasurePolicy.kt#9igjgp");
        boolean zChanged2 = composer.changed(lazyStaggeredGridState) | ((((i & 112) ^ 48) > 32 && composer.changed(function0)) || (i & 48) == 32) | ((((i & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(paddingValues)) || (i & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | ((((i & 7168) ^ 3072) > 2048 && composer.changed(z)) || (i & 3072) == 2048);
        if (((57344 & i) ^ 24576) > 16384) {
            orientation2 = orientation;
            if (composer.changed(orientation2)) {
                z2 = true;
            }
            boolean z4 = zChanged2 | z2;
            if (((458752 & i) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072 && composer.changed(f)) {
                z3 = true;
            } else if ((196608 & i) == 131072) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z5 = z4 | z3 | ((((3670016 & i) ^ 1572864) <= 1048576 && composer.changed(f2)) || (i & 1572864) == 1048576);
            if (((234881024 & i) ^ 100663296) > 67108864) {
                lazyGridStaggeredGridSlotsProvider2 = lazyGridStaggeredGridSlotsProvider;
                if (!composer.changed(lazyGridStaggeredGridSlotsProvider2)) {
                }
                zChanged = z5 | z | composer.changed(graphicsContext);
                objRememberedValue = composer.rememberedValue();
                if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    final Orientation orientation3 = orientation2;
                    final LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider3 = lazyGridStaggeredGridSlotsProvider2;
                    objRememberedValue = (Function2) new Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ LazyStaggeredGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                            return m940invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                        }

                        /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                        public final LazyStaggeredGridMeasureResult m940invoke0kLqBqw(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j) {
                            long jIntOffset;
                            ObservableScopeInvalidator.m901attachToScopeimpl(lazyStaggeredGridState.m945getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                            CheckScrollableContainerConstraintsKt.m297checkScrollableContainerConstraintsK40F9xA(j, orientation3);
                            LazyStaggeredGridSlots lazyStaggeredGridSlotsMo921invoke0kLqBqw = lazyGridStaggeredGridSlotsProvider3.mo921invoke0kLqBqw(lazyLayoutMeasureScope, j);
                            boolean z6 = orientation3 == Orientation.Vertical;
                            LazyStaggeredGridItemProvider lazyStaggeredGridItemProviderInvoke = function0.invoke();
                            int i2 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation3, z, lazyLayoutMeasureScope.getLayoutDirection()));
                            int i3 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation3, z, lazyLayoutMeasureScope.getLayoutDirection()));
                            int i4 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation3, lazyLayoutMeasureScope.getLayoutDirection()));
                            int iM6832getMaxHeightimpl = ((z6 ? Constraints.m6832getMaxHeightimpl(j) : Constraints.m6833getMaxWidthimpl(j)) - i2) - i3;
                            if (z6) {
                                jIntOffset = IntOffsetKt.IntOffset(i4, i2);
                            } else {
                                jIntOffset = IntOffsetKt.IntOffset(i2, i4);
                            }
                            long j2 = jIntOffset;
                            PaddingValues paddingValues2 = paddingValues;
                            int i5 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(PaddingKt.calculateStartPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection()) + PaddingKt.calculateEndPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection())));
                            PaddingValues paddingValues3 = paddingValues;
                            boolean z7 = z6;
                            LazyStaggeredGridMeasureResult lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ = LazyStaggeredGridMeasureKt.m938measureStaggeredGridXtK8cYQ(lazyLayoutMeasureScope, lazyStaggeredGridState, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo()), lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridSlotsMo921invoke0kLqBqw, Constraints.m6823copyZbe2FdA$default(j, ConstraintsKt.m6850constrainWidthK40F9xA(j, i5), 0, ConstraintsKt.m6849constrainHeightK40F9xA(j, lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(paddingValues3.getTop() + paddingValues3.getBottom()))), 0, 10, null), z7, z, j2, iM6832getMaxHeightimpl, lazyLayoutMeasureScope.mo400roundToPx0680j_4(f), i2, i3, coroutineScope, graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation_release$default(lazyStaggeredGridState, lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ, false, 2, null);
                            return lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ;
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2 = (Function2) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                return function2;
            }
            lazyGridStaggeredGridSlotsProvider2 = lazyGridStaggeredGridSlotsProvider;
            boolean z6 = (i & 100663296) == 67108864;
            zChanged = z5 | z6 | composer.changed(graphicsContext);
            objRememberedValue = composer.rememberedValue();
            if (!zChanged) {
                final Orientation orientation4 = orientation2;
                final LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider4 = lazyGridStaggeredGridSlotsProvider2;
                objRememberedValue = (Function2) new Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ LazyStaggeredGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                        return m940invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                    }

                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final LazyStaggeredGridMeasureResult m940invoke0kLqBqw(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j) {
                        long jIntOffset;
                        ObservableScopeInvalidator.m901attachToScopeimpl(lazyStaggeredGridState.m945getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                        CheckScrollableContainerConstraintsKt.m297checkScrollableContainerConstraintsK40F9xA(j, orientation4);
                        LazyStaggeredGridSlots lazyStaggeredGridSlotsMo921invoke0kLqBqw = lazyGridStaggeredGridSlotsProvider4.mo921invoke0kLqBqw(lazyLayoutMeasureScope, j);
                        boolean z7 = orientation4 == Orientation.Vertical;
                        LazyStaggeredGridItemProvider lazyStaggeredGridItemProviderInvoke = function0.invoke();
                        int i2 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation4, z, lazyLayoutMeasureScope.getLayoutDirection()));
                        int i3 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation4, z, lazyLayoutMeasureScope.getLayoutDirection()));
                        int i4 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation4, lazyLayoutMeasureScope.getLayoutDirection()));
                        int iM6832getMaxHeightimpl = ((z7 ? Constraints.m6832getMaxHeightimpl(j) : Constraints.m6833getMaxWidthimpl(j)) - i2) - i3;
                        if (z7) {
                            jIntOffset = IntOffsetKt.IntOffset(i4, i2);
                        } else {
                            jIntOffset = IntOffsetKt.IntOffset(i2, i4);
                        }
                        long j2 = jIntOffset;
                        PaddingValues paddingValues2 = paddingValues;
                        int i5 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(PaddingKt.calculateStartPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection()) + PaddingKt.calculateEndPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection())));
                        PaddingValues paddingValues3 = paddingValues;
                        boolean z8 = z7;
                        LazyStaggeredGridMeasureResult lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ = LazyStaggeredGridMeasureKt.m938measureStaggeredGridXtK8cYQ(lazyLayoutMeasureScope, lazyStaggeredGridState, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo()), lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridSlotsMo921invoke0kLqBqw, Constraints.m6823copyZbe2FdA$default(j, ConstraintsKt.m6850constrainWidthK40F9xA(j, i5), 0, ConstraintsKt.m6849constrainHeightK40F9xA(j, lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(paddingValues3.getTop() + paddingValues3.getBottom()))), 0, 10, null), z8, z, j2, iM6832getMaxHeightimpl, lazyLayoutMeasureScope.mo400roundToPx0680j_4(f), i2, i3, coroutineScope, graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation_release$default(lazyStaggeredGridState, lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ, false, 2, null);
                        return lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ;
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            } else {
                final Orientation orientation5 = orientation2;
                final LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider5 = lazyGridStaggeredGridSlotsProvider2;
                objRememberedValue = (Function2) new Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ LazyStaggeredGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                        return m940invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                    }

                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final LazyStaggeredGridMeasureResult m940invoke0kLqBqw(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j) {
                        long jIntOffset;
                        ObservableScopeInvalidator.m901attachToScopeimpl(lazyStaggeredGridState.m945getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                        CheckScrollableContainerConstraintsKt.m297checkScrollableContainerConstraintsK40F9xA(j, orientation5);
                        LazyStaggeredGridSlots lazyStaggeredGridSlotsMo921invoke0kLqBqw = lazyGridStaggeredGridSlotsProvider5.mo921invoke0kLqBqw(lazyLayoutMeasureScope, j);
                        boolean z7 = orientation5 == Orientation.Vertical;
                        LazyStaggeredGridItemProvider lazyStaggeredGridItemProviderInvoke = function0.invoke();
                        int i2 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation5, z, lazyLayoutMeasureScope.getLayoutDirection()));
                        int i3 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation5, z, lazyLayoutMeasureScope.getLayoutDirection()));
                        int i4 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation5, lazyLayoutMeasureScope.getLayoutDirection()));
                        int iM6832getMaxHeightimpl = ((z7 ? Constraints.m6832getMaxHeightimpl(j) : Constraints.m6833getMaxWidthimpl(j)) - i2) - i3;
                        if (z7) {
                            jIntOffset = IntOffsetKt.IntOffset(i4, i2);
                        } else {
                            jIntOffset = IntOffsetKt.IntOffset(i2, i4);
                        }
                        long j2 = jIntOffset;
                        PaddingValues paddingValues2 = paddingValues;
                        int i5 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(PaddingKt.calculateStartPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection()) + PaddingKt.calculateEndPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection())));
                        PaddingValues paddingValues3 = paddingValues;
                        boolean z8 = z7;
                        LazyStaggeredGridMeasureResult lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ = LazyStaggeredGridMeasureKt.m938measureStaggeredGridXtK8cYQ(lazyLayoutMeasureScope, lazyStaggeredGridState, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo()), lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridSlotsMo921invoke0kLqBqw, Constraints.m6823copyZbe2FdA$default(j, ConstraintsKt.m6850constrainWidthK40F9xA(j, i5), 0, ConstraintsKt.m6849constrainHeightK40F9xA(j, lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(paddingValues3.getTop() + paddingValues3.getBottom()))), 0, 10, null), z8, z, j2, iM6832getMaxHeightimpl, lazyLayoutMeasureScope.mo400roundToPx0680j_4(f), i2, i3, coroutineScope, graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation_release$default(lazyStaggeredGridState, lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ, false, 2, null);
                        return lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ;
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function3 = (Function2) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            return function3;
        }
        orientation2 = orientation;
        if ((i & 24576) == 16384) {
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z7 = zChanged2 | z2;
        if (((458752 & i) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072) {
        }
        if ((196608 & i) == 131072) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z8 = z7 | z3 | ((((3670016 & i) ^ 1572864) <= 1048576 && composer.changed(f2)) || (i & 1572864) == 1048576);
        if (((234881024 & i) ^ 100663296) > 67108864) {
            lazyGridStaggeredGridSlotsProvider2 = lazyGridStaggeredGridSlotsProvider;
            if (!composer.changed(lazyGridStaggeredGridSlotsProvider2)) {
            }
            zChanged = z8 | z6 | composer.changed(graphicsContext);
            objRememberedValue = composer.rememberedValue();
            if (!zChanged) {
                final Orientation orientation6 = orientation2;
                final LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider6 = lazyGridStaggeredGridSlotsProvider2;
                objRememberedValue = (Function2) new Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ LazyStaggeredGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                        return m940invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                    }

                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final LazyStaggeredGridMeasureResult m940invoke0kLqBqw(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j) {
                        long jIntOffset;
                        ObservableScopeInvalidator.m901attachToScopeimpl(lazyStaggeredGridState.m945getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                        CheckScrollableContainerConstraintsKt.m297checkScrollableContainerConstraintsK40F9xA(j, orientation6);
                        LazyStaggeredGridSlots lazyStaggeredGridSlotsMo921invoke0kLqBqw = lazyGridStaggeredGridSlotsProvider6.mo921invoke0kLqBqw(lazyLayoutMeasureScope, j);
                        boolean z9 = orientation6 == Orientation.Vertical;
                        LazyStaggeredGridItemProvider lazyStaggeredGridItemProviderInvoke = function0.invoke();
                        int i2 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation6, z, lazyLayoutMeasureScope.getLayoutDirection()));
                        int i3 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation6, z, lazyLayoutMeasureScope.getLayoutDirection()));
                        int i4 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation6, lazyLayoutMeasureScope.getLayoutDirection()));
                        int iM6832getMaxHeightimpl = ((z9 ? Constraints.m6832getMaxHeightimpl(j) : Constraints.m6833getMaxWidthimpl(j)) - i2) - i3;
                        if (z9) {
                            jIntOffset = IntOffsetKt.IntOffset(i4, i2);
                        } else {
                            jIntOffset = IntOffsetKt.IntOffset(i2, i4);
                        }
                        long j2 = jIntOffset;
                        PaddingValues paddingValues2 = paddingValues;
                        int i5 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(PaddingKt.calculateStartPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection()) + PaddingKt.calculateEndPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection())));
                        PaddingValues paddingValues3 = paddingValues;
                        boolean z10 = z9;
                        LazyStaggeredGridMeasureResult lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ = LazyStaggeredGridMeasureKt.m938measureStaggeredGridXtK8cYQ(lazyLayoutMeasureScope, lazyStaggeredGridState, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo()), lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridSlotsMo921invoke0kLqBqw, Constraints.m6823copyZbe2FdA$default(j, ConstraintsKt.m6850constrainWidthK40F9xA(j, i5), 0, ConstraintsKt.m6849constrainHeightK40F9xA(j, lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(paddingValues3.getTop() + paddingValues3.getBottom()))), 0, 10, null), z10, z, j2, iM6832getMaxHeightimpl, lazyLayoutMeasureScope.mo400roundToPx0680j_4(f), i2, i3, coroutineScope, graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation_release$default(lazyStaggeredGridState, lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ, false, 2, null);
                        return lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ;
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            } else {
                final Orientation orientation7 = orientation2;
                final LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider7 = lazyGridStaggeredGridSlotsProvider2;
                objRememberedValue = (Function2) new Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ LazyStaggeredGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                        return m940invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                    }

                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final LazyStaggeredGridMeasureResult m940invoke0kLqBqw(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j) {
                        long jIntOffset;
                        ObservableScopeInvalidator.m901attachToScopeimpl(lazyStaggeredGridState.m945getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                        CheckScrollableContainerConstraintsKt.m297checkScrollableContainerConstraintsK40F9xA(j, orientation7);
                        LazyStaggeredGridSlots lazyStaggeredGridSlotsMo921invoke0kLqBqw = lazyGridStaggeredGridSlotsProvider7.mo921invoke0kLqBqw(lazyLayoutMeasureScope, j);
                        boolean z9 = orientation7 == Orientation.Vertical;
                        LazyStaggeredGridItemProvider lazyStaggeredGridItemProviderInvoke = function0.invoke();
                        int i2 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation7, z, lazyLayoutMeasureScope.getLayoutDirection()));
                        int i3 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation7, z, lazyLayoutMeasureScope.getLayoutDirection()));
                        int i4 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation7, lazyLayoutMeasureScope.getLayoutDirection()));
                        int iM6832getMaxHeightimpl = ((z9 ? Constraints.m6832getMaxHeightimpl(j) : Constraints.m6833getMaxWidthimpl(j)) - i2) - i3;
                        if (z9) {
                            jIntOffset = IntOffsetKt.IntOffset(i4, i2);
                        } else {
                            jIntOffset = IntOffsetKt.IntOffset(i2, i4);
                        }
                        long j2 = jIntOffset;
                        PaddingValues paddingValues2 = paddingValues;
                        int i5 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(PaddingKt.calculateStartPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection()) + PaddingKt.calculateEndPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection())));
                        PaddingValues paddingValues3 = paddingValues;
                        boolean z10 = z9;
                        LazyStaggeredGridMeasureResult lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ = LazyStaggeredGridMeasureKt.m938measureStaggeredGridXtK8cYQ(lazyLayoutMeasureScope, lazyStaggeredGridState, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo()), lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridSlotsMo921invoke0kLqBqw, Constraints.m6823copyZbe2FdA$default(j, ConstraintsKt.m6850constrainWidthK40F9xA(j, i5), 0, ConstraintsKt.m6849constrainHeightK40F9xA(j, lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(paddingValues3.getTop() + paddingValues3.getBottom()))), 0, 10, null), z10, z, j2, iM6832getMaxHeightimpl, lazyLayoutMeasureScope.mo400roundToPx0680j_4(f), i2, i3, coroutineScope, graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation_release$default(lazyStaggeredGridState, lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ, false, 2, null);
                        return lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ;
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function4 = (Function2) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            return function4;
        }
        lazyGridStaggeredGridSlotsProvider2 = lazyGridStaggeredGridSlotsProvider;
        if ((i & 100663296) == 67108864) {
        }
        zChanged = z8 | z6 | composer.changed(graphicsContext);
        objRememberedValue = composer.rememberedValue();
        if (!zChanged) {
            final Orientation orientation8 = orientation2;
            final LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider8 = lazyGridStaggeredGridSlotsProvider2;
            objRememberedValue = (Function2) new Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyStaggeredGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m940invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyStaggeredGridMeasureResult m940invoke0kLqBqw(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j) {
                    long jIntOffset;
                    ObservableScopeInvalidator.m901attachToScopeimpl(lazyStaggeredGridState.m945getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                    CheckScrollableContainerConstraintsKt.m297checkScrollableContainerConstraintsK40F9xA(j, orientation8);
                    LazyStaggeredGridSlots lazyStaggeredGridSlotsMo921invoke0kLqBqw = lazyGridStaggeredGridSlotsProvider8.mo921invoke0kLqBqw(lazyLayoutMeasureScope, j);
                    boolean z9 = orientation8 == Orientation.Vertical;
                    LazyStaggeredGridItemProvider lazyStaggeredGridItemProviderInvoke = function0.invoke();
                    int i2 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation8, z, lazyLayoutMeasureScope.getLayoutDirection()));
                    int i3 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation8, z, lazyLayoutMeasureScope.getLayoutDirection()));
                    int i4 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation8, lazyLayoutMeasureScope.getLayoutDirection()));
                    int iM6832getMaxHeightimpl = ((z9 ? Constraints.m6832getMaxHeightimpl(j) : Constraints.m6833getMaxWidthimpl(j)) - i2) - i3;
                    if (z9) {
                        jIntOffset = IntOffsetKt.IntOffset(i4, i2);
                    } else {
                        jIntOffset = IntOffsetKt.IntOffset(i2, i4);
                    }
                    long j2 = jIntOffset;
                    PaddingValues paddingValues2 = paddingValues;
                    int i5 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(PaddingKt.calculateStartPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection()) + PaddingKt.calculateEndPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection())));
                    PaddingValues paddingValues3 = paddingValues;
                    boolean z10 = z9;
                    LazyStaggeredGridMeasureResult lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ = LazyStaggeredGridMeasureKt.m938measureStaggeredGridXtK8cYQ(lazyLayoutMeasureScope, lazyStaggeredGridState, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo()), lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridSlotsMo921invoke0kLqBqw, Constraints.m6823copyZbe2FdA$default(j, ConstraintsKt.m6850constrainWidthK40F9xA(j, i5), 0, ConstraintsKt.m6849constrainHeightK40F9xA(j, lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(paddingValues3.getTop() + paddingValues3.getBottom()))), 0, 10, null), z10, z, j2, iM6832getMaxHeightimpl, lazyLayoutMeasureScope.mo400roundToPx0680j_4(f), i2, i3, coroutineScope, graphicsContext);
                    LazyStaggeredGridState.applyMeasureResult$foundation_release$default(lazyStaggeredGridState, lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ, false, 2, null);
                    return lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        } else {
            final Orientation orientation9 = orientation2;
            final LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider9 = lazyGridStaggeredGridSlotsProvider2;
            objRememberedValue = (Function2) new Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyStaggeredGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m940invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyStaggeredGridMeasureResult m940invoke0kLqBqw(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j) {
                    long jIntOffset;
                    ObservableScopeInvalidator.m901attachToScopeimpl(lazyStaggeredGridState.m945getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                    CheckScrollableContainerConstraintsKt.m297checkScrollableContainerConstraintsK40F9xA(j, orientation9);
                    LazyStaggeredGridSlots lazyStaggeredGridSlotsMo921invoke0kLqBqw = lazyGridStaggeredGridSlotsProvider9.mo921invoke0kLqBqw(lazyLayoutMeasureScope, j);
                    boolean z9 = orientation9 == Orientation.Vertical;
                    LazyStaggeredGridItemProvider lazyStaggeredGridItemProviderInvoke = function0.invoke();
                    int i2 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation9, z, lazyLayoutMeasureScope.getLayoutDirection()));
                    int i3 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation9, z, lazyLayoutMeasureScope.getLayoutDirection()));
                    int i4 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation9, lazyLayoutMeasureScope.getLayoutDirection()));
                    int iM6832getMaxHeightimpl = ((z9 ? Constraints.m6832getMaxHeightimpl(j) : Constraints.m6833getMaxWidthimpl(j)) - i2) - i3;
                    if (z9) {
                        jIntOffset = IntOffsetKt.IntOffset(i4, i2);
                    } else {
                        jIntOffset = IntOffsetKt.IntOffset(i2, i4);
                    }
                    long j2 = jIntOffset;
                    PaddingValues paddingValues2 = paddingValues;
                    int i5 = lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(PaddingKt.calculateStartPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection()) + PaddingKt.calculateEndPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection())));
                    PaddingValues paddingValues3 = paddingValues;
                    boolean z10 = z9;
                    LazyStaggeredGridMeasureResult lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ = LazyStaggeredGridMeasureKt.m938measureStaggeredGridXtK8cYQ(lazyLayoutMeasureScope, lazyStaggeredGridState, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo()), lazyStaggeredGridItemProviderInvoke, lazyStaggeredGridSlotsMo921invoke0kLqBqw, Constraints.m6823copyZbe2FdA$default(j, ConstraintsKt.m6850constrainWidthK40F9xA(j, i5), 0, ConstraintsKt.m6849constrainHeightK40F9xA(j, lazyLayoutMeasureScope.mo400roundToPx0680j_4(Dp.m6890constructorimpl(paddingValues3.getTop() + paddingValues3.getBottom()))), 0, 10, null), z10, z, j2, iM6832getMaxHeightimpl, lazyLayoutMeasureScope.mo400roundToPx0680j_4(f), i2, i3, coroutineScope, graphicsContext);
                    LazyStaggeredGridState.applyMeasureResult$foundation_release$default(lazyStaggeredGridState, lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ, false, 2, null);
                    return lazyStaggeredGridMeasureResultM938measureStaggeredGridXtK8cYQ;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function5 = (Function2) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return function5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float startPadding(PaddingValues paddingValues, Orientation orientation, LayoutDirection layoutDirection) {
        int i = WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()];
        if (i == 1) {
            return PaddingKt.calculateStartPadding(paddingValues, layoutDirection);
        }
        if (i == 2) {
            return paddingValues.getTop();
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float beforePadding(PaddingValues paddingValues, Orientation orientation, boolean z, LayoutDirection layoutDirection) {
        int i = WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()];
        if (i == 1) {
            return z ? paddingValues.getBottom() : paddingValues.getTop();
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        if (z) {
            return PaddingKt.calculateEndPadding(paddingValues, layoutDirection);
        }
        return PaddingKt.calculateStartPadding(paddingValues, layoutDirection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float afterPadding(PaddingValues paddingValues, Orientation orientation, boolean z, LayoutDirection layoutDirection) {
        int i = WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()];
        if (i == 1) {
            return z ? paddingValues.getTop() : paddingValues.getBottom();
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        if (z) {
            return PaddingKt.calculateStartPadding(paddingValues, layoutDirection);
        }
        return PaddingKt.calculateEndPadding(paddingValues, layoutDirection);
    }
}
