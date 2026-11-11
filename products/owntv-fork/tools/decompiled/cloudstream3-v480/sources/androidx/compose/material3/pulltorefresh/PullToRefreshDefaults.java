package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.tokens.ElevationTokens;
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
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PullToRefresh.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JJ\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fR\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0017\u0010\n\u001a\u00020\u000b8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u000b8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshDefaults;", "", "()V", "Elevation", "Landroidx/compose/ui/unit/Dp;", "getElevation-D9Ej5fM", "()F", "F", "PositionalThreshold", "getPositionalThreshold-D9Ej5fM", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "indicatorColor", "getIndicatorColor", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "Indicator", "", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "isRefreshing", "", "modifier", "Landroidx/compose/ui/Modifier;", "color", "threshold", "Indicator-2poqoh4", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;ZLandroidx/compose/ui/Modifier;JJFLandroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PullToRefreshDefaults {
    public static final int $stable = 0;
    public static final PullToRefreshDefaults INSTANCE = new PullToRefreshDefaults();
    private static final Shape shape = RoundedCornerShapeKt.getCircleShape();
    private static final float PositionalThreshold = Dp.m6890constructorimpl(80.0f);
    private static final float Elevation = ElevationTokens.INSTANCE.m2923getLevel2D9Ej5fM();

    private PullToRefreshDefaults() {
    }

    public final Shape getShape() {
        return shape;
    }

    public final long getContainerColor(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1066257972, "C419@15907L11:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1066257972, i, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.<get-containerColor> (PullToRefresh.kt:419)");
        }
        long surfaceContainerHigh = MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurfaceContainerHigh();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return surfaceContainerHigh;
    }

    public final long getIndicatorColor(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -1441334156, "C423@16068L11:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1441334156, i, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.<get-indicatorColor> (PullToRefresh.kt:423)");
        }
        long onSurfaceVariant = MaterialTheme.INSTANCE.getColorScheme(composer, 6).getOnSurfaceVariant();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return onSurfaceVariant;
    }

    /* JADX INFO: renamed from: getPositionalThreshold-D9Ej5fM, reason: not valid java name */
    public final float m2734getPositionalThresholdD9Ej5fM() {
        return PositionalThreshold;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m2733getElevationD9Ej5fM() {
        return Elevation;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0119 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:101:0x011b  */
    /* JADX WARN: Code duplicated, block: B:102:0x0120  */
    /* JADX WARN: Code duplicated, block: B:105:0x0125  */
    /* JADX WARN: Code duplicated, block: B:106:0x012e  */
    /* JADX WARN: Code duplicated, block: B:109:0x0133  */
    /* JADX WARN: Code duplicated, block: B:110:0x013c  */
    /* JADX WARN: Code duplicated, block: B:113:0x0141  */
    /* JADX WARN: Code duplicated, block: B:114:0x0148 A[PHI: r2 r4 r5 r7
      0x0148: PHI (r2v36 int) = (r2v21 int), (r2v39 int) binds: [B:112:0x013f, B:99:0x0115] A[DONT_GENERATE, DONT_INLINE]
      0x0148: PHI (r4v17 androidx.compose.ui.Modifier) = (r4v6 androidx.compose.ui.Modifier), (r4v21 androidx.compose.ui.Modifier) binds: [B:112:0x013f, B:99:0x0115] A[DONT_GENERATE, DONT_INLINE]
      0x0148: PHI (r5v19 long) = (r5v5 long), (r5v20 long) binds: [B:112:0x013f, B:99:0x0115] A[DONT_GENERATE, DONT_INLINE]
      0x0148: PHI (r7v16 long) = (r7v2 long), (r7v17 long) binds: [B:112:0x013f, B:99:0x0115] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:117:0x0153  */
    /* JADX WARN: Code duplicated, block: B:120:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:123:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:124:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:127:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:129:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:132:0x0261  */
    /* JADX WARN: Code duplicated, block: B:136:0x0271  */
    /* JADX WARN: Code duplicated, block: B:138:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0049  */
    /* JADX WARN: Code duplicated, block: B:27:0x004c  */
    /* JADX WARN: Code duplicated, block: B:29:0x0050  */
    /* JADX WARN: Code duplicated, block: B:31:0x0058  */
    /* JADX WARN: Code duplicated, block: B:32:0x005b  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x0069  */
    /* JADX WARN: Code duplicated, block: B:41:0x0071  */
    /* JADX WARN: Code duplicated, block: B:42:0x0074  */
    /* JADX WARN: Code duplicated, block: B:45:0x007a  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0095  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:73:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:76:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:85:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f9  */
    /* JADX INFO: renamed from: Indicator-2poqoh4, reason: not valid java name */
    public final void m2732Indicator2poqoh4(final PullToRefreshState pullToRefreshState, final boolean z, Modifier modifier, long j, long j2, float f, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        Modifier modifier2;
        int i5;
        long j3;
        long j4;
        float f2;
        int i6;
        Modifier.Companion companion;
        long containerColor;
        long indicatorColor;
        int i7;
        float f3;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM3454constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        Composer composer2;
        final Modifier modifier3;
        final long j5;
        final long j6;
        final float f4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1076870256);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Indicator)P(4,2,3,1:c#ui.graphics.Color,0:c#ui.graphics.Color,5:c#ui.unit.Dp)437@16566L14,438@16627L14,441@16704L1007:PullToRefresh.kt#djiw08");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(pullToRefreshState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) == 0) {
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        j3 = j;
                        int i8 = composerStartRestartGroup.changed(j3) ? 2048 : 1024;
                        i3 |= i8;
                    } else {
                        j3 = j;
                    }
                    i3 |= i8;
                } else {
                    j3 = j;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        j4 = j2;
                        int i9 = composerStartRestartGroup.changed(j4) ? 16384 : 8192;
                        i3 |= i9;
                    } else {
                        j4 = j2;
                    }
                    i3 |= i9;
                } else {
                    j4 = j2;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        f2 = f;
                        int i10 = composerStartRestartGroup.changed(f2) ? 131072 : 65536;
                        i3 |= i10;
                    } else {
                        f2 = f;
                    }
                    i3 |= i10;
                } else {
                    f2 = f;
                }
                if ((i2 & 64) != 0) {
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i6 = 1048576;
                        } else {
                            i6 = 524288;
                        }
                        i3 |= i6;
                    }
                    if ((599187 & i3) == 599186 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                            }
                            companion = modifier2;
                            containerColor = j3;
                            indicatorColor = j4;
                        } else {
                            if (i4 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if ((i2 & 8) != 0) {
                                containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                containerColor = j3;
                            }
                            if ((i2 & 16) != 0) {
                                indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                indicatorColor = j4;
                            }
                            if ((i2 & 32) != 0) {
                                i7 = i3 & (-458753);
                                f3 = PositionalThreshold;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                            }
                            final long j7 = indicatorColor;
                            Modifier modifier4 = companion;
                            int i11 = i7;
                            Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier4, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                            float f5 = f3;
                            long j8 = containerColor;
                            Alignment center = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                            composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                            CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                                    invoke(bool.booleanValue(), composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(boolean z2, Composer composer3, int i12) {
                                    ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                                    if ((i12 & 6) == 0) {
                                        i12 |= composer3.changed(z2) ? 4 : 2;
                                    }
                                    if ((i12 & 19) != 18 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(167807595, i12, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                                        }
                                        if (z2) {
                                            composer3.startReplaceGroup(576835739);
                                            ComposerKt.sourceInformation(composer3, "456@17267L201");
                                            ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j7, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                            composer3.endReplaceGroup();
                                        } else {
                                            composer3.startReplaceGroup(577079337);
                                            ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                            ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                            boolean zChanged = composer3.changed(pullToRefreshState);
                                            final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                            Object objRememberedValue = composer3.rememberedValue();
                                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Float invoke() {
                                                        return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                                    }
                                                };
                                                composer3.updateRememberedValue(objRememberedValue);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j7, composer3, 0);
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
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i11 >> 3) & 14) | 24960, 10);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            composerStartRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2 = composerStartRestartGroup;
                            modifier3 = modifier4;
                            j5 = j7;
                            j6 = j8;
                            f4 = f5;
                        }
                        f3 = f2;
                        i7 = i3;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                        }
                        final long j9 = indicatorColor;
                        Modifier modifier5 = companion;
                        int i12 = i7;
                        Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default2 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier5, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                        float f6 = f3;
                        long j10 = containerColor;
                        Alignment center2 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default2);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                        CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                                invoke(bool.booleanValue(), composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z2, Composer composer3, int i13) {
                                ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                                if ((i13 & 6) == 0) {
                                    i13 |= composer3.changed(z2) ? 4 : 2;
                                }
                                if ((i13 & 19) != 18 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(167807595, i13, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                                    }
                                    if (z2) {
                                        composer3.startReplaceGroup(576835739);
                                        ComposerKt.sourceInformation(composer3, "456@17267L201");
                                        ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j9, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                        composer3.endReplaceGroup();
                                    } else {
                                        composer3.startReplaceGroup(577079337);
                                        ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                        boolean zChanged = composer3.changed(pullToRefreshState);
                                        final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                        Object objRememberedValue = composer3.rememberedValue();
                                        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Float invoke() {
                                                    return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j9, composer3, 0);
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
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 >> 3) & 14) | 24960, 10);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2 = composerStartRestartGroup;
                        modifier3 = modifier5;
                        j5 = j9;
                        j6 = j10;
                        f4 = f6;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        j6 = j3;
                        j5 = j4;
                        composer2 = composerStartRestartGroup;
                        f4 = f2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i13) {
                                this.$tmp0_rcvr.m2732Indicator2poqoh4(pullToRefreshState, z, modifier3, j6, j5, f4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    } else {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                    }
                    final long j11 = indicatorColor;
                    Modifier modifier6 = companion;
                    int i13 = i7;
                    Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default3 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier6, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                    float f7 = f3;
                    long j12 = containerColor;
                    Alignment center3 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(center3, false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default3);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                    CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                            invoke(bool.booleanValue(), composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z2, Composer composer3, int i14) {
                            ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                            if ((i14 & 6) == 0) {
                                i14 |= composer3.changed(z2) ? 4 : 2;
                            }
                            if ((i14 & 19) != 18 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(167807595, i14, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                                }
                                if (z2) {
                                    composer3.startReplaceGroup(576835739);
                                    ComposerKt.sourceInformation(composer3, "456@17267L201");
                                    ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j11, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                    composer3.endReplaceGroup();
                                } else {
                                    composer3.startReplaceGroup(577079337);
                                    ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                    boolean zChanged = composer3.changed(pullToRefreshState);
                                    final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                    Object objRememberedValue = composer3.rememberedValue();
                                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Float invoke() {
                                                return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j11, composer3, 0);
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
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i13 >> 3) & 14) | 24960, 10);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2 = composerStartRestartGroup;
                    modifier3 = modifier6;
                    j5 = j11;
                    j6 = j12;
                    f4 = f7;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    } else {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                    }
                    final long j13 = indicatorColor;
                    Modifier modifier7 = companion;
                    int i14 = i7;
                    Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default4 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier7, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                    float f8 = f3;
                    long j14 = containerColor;
                    Alignment center4 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(center4, false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default4);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                    CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                            invoke(bool.booleanValue(), composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z2, Composer composer3, int i15) {
                            ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                            if ((i15 & 6) == 0) {
                                i15 |= composer3.changed(z2) ? 4 : 2;
                            }
                            if ((i15 & 19) != 18 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(167807595, i15, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                                }
                                if (z2) {
                                    composer3.startReplaceGroup(576835739);
                                    ComposerKt.sourceInformation(composer3, "456@17267L201");
                                    ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j13, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                    composer3.endReplaceGroup();
                                } else {
                                    composer3.startReplaceGroup(577079337);
                                    ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                    boolean zChanged = composer3.changed(pullToRefreshState);
                                    final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                    Object objRememberedValue = composer3.rememberedValue();
                                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Float invoke() {
                                                return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j13, composer3, 0);
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
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i14 >> 3) & 14) | 24960, 10);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2 = composerStartRestartGroup;
                    modifier3 = modifier7;
                    j5 = j13;
                    j6 = j14;
                    f4 = f8;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$2
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
                            this.$tmp0_rcvr.m2732Indicator2poqoh4(pullToRefreshState, z, modifier3, j6, j5, f4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            modifier2 = modifier;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    j3 = j;
                    if (composerStartRestartGroup.changed(j3)) {
                    }
                    i3 |= i8;
                } else {
                    j3 = j;
                }
                i3 |= i8;
            } else {
                j3 = j;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    j4 = j2;
                    if (composerStartRestartGroup.changed(j4)) {
                    }
                    i3 |= i9;
                } else {
                    j4 = j2;
                }
                i3 |= i9;
            } else {
                j4 = j2;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    f2 = f;
                    if (composerStartRestartGroup.changed(f2)) {
                    }
                    i3 |= i10;
                } else {
                    f2 = f;
                }
                i3 |= i10;
            } else {
                f2 = f;
            }
            if ((i2 & 64) != 0) {
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i6 = 1048576;
                    } else {
                        i6 = 524288;
                    }
                    i3 |= i6;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    } else {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                    }
                    final long j15 = indicatorColor;
                    Modifier modifier8 = companion;
                    int i15 = i7;
                    Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default5 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier8, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                    float f9 = f3;
                    long j16 = containerColor;
                    Alignment center5 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(center5, false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default5);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                    CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                            invoke(bool.booleanValue(), composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z2, Composer composer3, int i16) {
                            ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                            if ((i16 & 6) == 0) {
                                i16 |= composer3.changed(z2) ? 4 : 2;
                            }
                            if ((i16 & 19) != 18 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(167807595, i16, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                                }
                                if (z2) {
                                    composer3.startReplaceGroup(576835739);
                                    ComposerKt.sourceInformation(composer3, "456@17267L201");
                                    ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j15, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                    composer3.endReplaceGroup();
                                } else {
                                    composer3.startReplaceGroup(577079337);
                                    ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                    boolean zChanged = composer3.changed(pullToRefreshState);
                                    final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                    Object objRememberedValue = composer3.rememberedValue();
                                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Float invoke() {
                                                return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j15, composer3, 0);
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
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i15 >> 3) & 14) | 24960, 10);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2 = composerStartRestartGroup;
                    modifier3 = modifier8;
                    j5 = j15;
                    j6 = j16;
                    f4 = f9;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    } else {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                    }
                    final long j17 = indicatorColor;
                    Modifier modifier9 = companion;
                    int i16 = i7;
                    Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default6 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier9, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                    float f10 = f3;
                    long j18 = containerColor;
                    Alignment center6 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(center6, false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default6);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                    CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                            invoke(bool.booleanValue(), composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z2, Composer composer3, int i17) {
                            ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                            if ((i17 & 6) == 0) {
                                i17 |= composer3.changed(z2) ? 4 : 2;
                            }
                            if ((i17 & 19) != 18 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(167807595, i17, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                                }
                                if (z2) {
                                    composer3.startReplaceGroup(576835739);
                                    ComposerKt.sourceInformation(composer3, "456@17267L201");
                                    ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j17, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                    composer3.endReplaceGroup();
                                } else {
                                    composer3.startReplaceGroup(577079337);
                                    ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                    boolean zChanged = composer3.changed(pullToRefreshState);
                                    final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                    Object objRememberedValue = composer3.rememberedValue();
                                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Float invoke() {
                                                return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j17, composer3, 0);
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
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 3) & 14) | 24960, 10);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2 = composerStartRestartGroup;
                    modifier3 = modifier9;
                    j5 = j17;
                    j6 = j18;
                    f4 = f10;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$2
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
                            this.$tmp0_rcvr.m2732Indicator2poqoh4(pullToRefreshState, z, modifier3, j6, j5, f4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                } else {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                }
                final long j19 = indicatorColor;
                Modifier modifier10 = companion;
                int i17 = i7;
                Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default7 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier10, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                float f11 = f3;
                long j110 = containerColor;
                Alignment center7 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(center7, false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default7);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                        invoke(bool.booleanValue(), composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2, Composer composer3, int i18) {
                        ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                        if ((i18 & 6) == 0) {
                            i18 |= composer3.changed(z2) ? 4 : 2;
                        }
                        if ((i18 & 19) != 18 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(167807595, i18, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                            }
                            if (z2) {
                                composer3.startReplaceGroup(576835739);
                                ComposerKt.sourceInformation(composer3, "456@17267L201");
                                ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j19, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                composer3.endReplaceGroup();
                            } else {
                                composer3.startReplaceGroup(577079337);
                                ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                boolean zChanged = composer3.changed(pullToRefreshState);
                                final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Float invoke() {
                                            return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j19, composer3, 0);
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
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i17 >> 3) & 14) | 24960, 10);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2 = composerStartRestartGroup;
                modifier3 = modifier10;
                j5 = j19;
                j6 = j110;
                f4 = f11;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                } else {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                }
                final long j111 = indicatorColor;
                Modifier modifier11 = companion;
                int i18 = i7;
                Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default8 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier11, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                float f12 = f3;
                long j112 = containerColor;
                Alignment center8 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(center8, false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default8);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                        invoke(bool.booleanValue(), composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2, Composer composer3, int i19) {
                        ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                        if ((i19 & 6) == 0) {
                            i19 |= composer3.changed(z2) ? 4 : 2;
                        }
                        if ((i19 & 19) != 18 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(167807595, i19, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                            }
                            if (z2) {
                                composer3.startReplaceGroup(576835739);
                                ComposerKt.sourceInformation(composer3, "456@17267L201");
                                ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j111, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                composer3.endReplaceGroup();
                            } else {
                                composer3.startReplaceGroup(577079337);
                                ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                boolean zChanged = composer3.changed(pullToRefreshState);
                                final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Float invoke() {
                                            return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j111, composer3, 0);
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
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i18 >> 3) & 14) | 24960, 10);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2 = composerStartRestartGroup;
                modifier3 = modifier11;
                j5 = j111;
                j6 = j112;
                f4 = f12;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$2
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
                        this.$tmp0_rcvr.m2732Indicator2poqoh4(pullToRefreshState, z, modifier3, j6, j5, f4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    j3 = j;
                    if (composerStartRestartGroup.changed(j3)) {
                    }
                    i3 |= i8;
                } else {
                    j3 = j;
                }
                i3 |= i8;
            } else {
                j3 = j;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    j4 = j2;
                    if (composerStartRestartGroup.changed(j4)) {
                    }
                    i3 |= i9;
                } else {
                    j4 = j2;
                }
                i3 |= i9;
            } else {
                j4 = j2;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    f2 = f;
                    if (composerStartRestartGroup.changed(f2)) {
                    }
                    i3 |= i10;
                } else {
                    f2 = f;
                }
                i3 |= i10;
            } else {
                f2 = f;
            }
            if ((i2 & 64) != 0) {
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i6 = 1048576;
                    } else {
                        i6 = 524288;
                    }
                    i3 |= i6;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    } else {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                    }
                    final long j113 = indicatorColor;
                    Modifier modifier12 = companion;
                    int i19 = i7;
                    Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default9 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier12, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                    float f13 = f3;
                    long j114 = containerColor;
                    Alignment center9 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy9 = BoxKt.maybeCachedBoxMeasurePolicy(center9, false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default9);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance9 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                    CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                            invoke(bool.booleanValue(), composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z2, Composer composer3, int i110) {
                            ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                            if ((i110 & 6) == 0) {
                                i110 |= composer3.changed(z2) ? 4 : 2;
                            }
                            if ((i110 & 19) != 18 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(167807595, i110, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                                }
                                if (z2) {
                                    composer3.startReplaceGroup(576835739);
                                    ComposerKt.sourceInformation(composer3, "456@17267L201");
                                    ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j113, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                    composer3.endReplaceGroup();
                                } else {
                                    composer3.startReplaceGroup(577079337);
                                    ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                    boolean zChanged = composer3.changed(pullToRefreshState);
                                    final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                    Object objRememberedValue = composer3.rememberedValue();
                                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Float invoke() {
                                                return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j113, composer3, 0);
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
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i19 >> 3) & 14) | 24960, 10);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2 = composerStartRestartGroup;
                    modifier3 = modifier12;
                    j5 = j113;
                    j6 = j114;
                    f4 = f13;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    } else {
                        if (i4 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 8) != 0) {
                            containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            indicatorColor = j4;
                        }
                        if ((i2 & 32) != 0) {
                            i7 = i3 & (-458753);
                            f3 = PositionalThreshold;
                        } else {
                            f3 = f2;
                            i7 = i3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                    }
                    final long j115 = indicatorColor;
                    Modifier modifier13 = companion;
                    int i110 = i7;
                    Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default10 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier13, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                    float f14 = f3;
                    long j116 = containerColor;
                    Alignment center10 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy10 = BoxKt.maybeCachedBoxMeasurePolicy(center10, false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default10);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance10 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                    CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                            invoke(bool.booleanValue(), composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z2, Composer composer3, int i111) {
                            ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                            if ((i111 & 6) == 0) {
                                i111 |= composer3.changed(z2) ? 4 : 2;
                            }
                            if ((i111 & 19) != 18 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(167807595, i111, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                                }
                                if (z2) {
                                    composer3.startReplaceGroup(576835739);
                                    ComposerKt.sourceInformation(composer3, "456@17267L201");
                                    ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j115, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                    composer3.endReplaceGroup();
                                } else {
                                    composer3.startReplaceGroup(577079337);
                                    ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                    boolean zChanged = composer3.changed(pullToRefreshState);
                                    final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                    Object objRememberedValue = composer3.rememberedValue();
                                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Float invoke() {
                                                return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j115, composer3, 0);
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
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i110 >> 3) & 14) | 24960, 10);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2 = composerStartRestartGroup;
                    modifier3 = modifier13;
                    j5 = j115;
                    j6 = j116;
                    f4 = f14;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$2
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
                            this.$tmp0_rcvr.m2732Indicator2poqoh4(pullToRefreshState, z, modifier3, j6, j5, f4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                } else {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                }
                final long j117 = indicatorColor;
                Modifier modifier14 = companion;
                int i111 = i7;
                Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default11 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier14, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                float f15 = f3;
                long j118 = containerColor;
                Alignment center11 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy11 = BoxKt.maybeCachedBoxMeasurePolicy(center11, false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default11);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy11, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier11, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance11 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                        invoke(bool.booleanValue(), composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2, Composer composer3, int i112) {
                        ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                        if ((i112 & 6) == 0) {
                            i112 |= composer3.changed(z2) ? 4 : 2;
                        }
                        if ((i112 & 19) != 18 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(167807595, i112, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                            }
                            if (z2) {
                                composer3.startReplaceGroup(576835739);
                                ComposerKt.sourceInformation(composer3, "456@17267L201");
                                ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j117, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                composer3.endReplaceGroup();
                            } else {
                                composer3.startReplaceGroup(577079337);
                                ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                boolean zChanged = composer3.changed(pullToRefreshState);
                                final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Float invoke() {
                                            return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j117, composer3, 0);
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
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i111 >> 3) & 14) | 24960, 10);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2 = composerStartRestartGroup;
                modifier3 = modifier14;
                j5 = j117;
                j6 = j118;
                f4 = f15;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                } else {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                }
                final long j119 = indicatorColor;
                Modifier modifier15 = companion;
                int i112 = i7;
                Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default12 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier15, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                float f16 = f3;
                long j1110 = containerColor;
                Alignment center12 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy12 = BoxKt.maybeCachedBoxMeasurePolicy(center12, false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default12);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy12, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap12, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier12, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance12 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                        invoke(bool.booleanValue(), composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2, Composer composer3, int i113) {
                        ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                        if ((i113 & 6) == 0) {
                            i113 |= composer3.changed(z2) ? 4 : 2;
                        }
                        if ((i113 & 19) != 18 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(167807595, i113, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                            }
                            if (z2) {
                                composer3.startReplaceGroup(576835739);
                                ComposerKt.sourceInformation(composer3, "456@17267L201");
                                ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j119, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                composer3.endReplaceGroup();
                            } else {
                                composer3.startReplaceGroup(577079337);
                                ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                boolean zChanged = composer3.changed(pullToRefreshState);
                                final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Float invoke() {
                                            return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j119, composer3, 0);
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
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i112 >> 3) & 14) | 24960, 10);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2 = composerStartRestartGroup;
                modifier3 = modifier15;
                j5 = j119;
                j6 = j1110;
                f4 = f16;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$2
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
                        this.$tmp0_rcvr.m2732Indicator2poqoh4(pullToRefreshState, z, modifier3, j6, j5, f4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        modifier2 = modifier;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                j3 = j;
                if (composerStartRestartGroup.changed(j3)) {
                }
                i3 |= i8;
            } else {
                j3 = j;
            }
            i3 |= i8;
        } else {
            j3 = j;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                j4 = j2;
                if (composerStartRestartGroup.changed(j4)) {
                }
                i3 |= i9;
            } else {
                j4 = j2;
            }
            i3 |= i9;
        } else {
            j4 = j2;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                f2 = f;
                if (composerStartRestartGroup.changed(f2)) {
                }
                i3 |= i10;
            } else {
                f2 = f;
            }
            i3 |= i10;
        } else {
            f2 = f;
        }
        if ((i2 & 64) != 0) {
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i6 = 1048576;
                } else {
                    i6 = 524288;
                }
                i3 |= i6;
            }
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                } else {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                }
                final long j1111 = indicatorColor;
                Modifier modifier16 = companion;
                int i113 = i7;
                Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default13 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier16, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                float f17 = f3;
                long j1112 = containerColor;
                Alignment center13 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy13 = BoxKt.maybeCachedBoxMeasurePolicy(center13, false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default13);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy13, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap13, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier13, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance13 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                        invoke(bool.booleanValue(), composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2, Composer composer3, int i114) {
                        ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                        if ((i114 & 6) == 0) {
                            i114 |= composer3.changed(z2) ? 4 : 2;
                        }
                        if ((i114 & 19) != 18 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(167807595, i114, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                            }
                            if (z2) {
                                composer3.startReplaceGroup(576835739);
                                ComposerKt.sourceInformation(composer3, "456@17267L201");
                                ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j1111, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                composer3.endReplaceGroup();
                            } else {
                                composer3.startReplaceGroup(577079337);
                                ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                boolean zChanged = composer3.changed(pullToRefreshState);
                                final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Float invoke() {
                                            return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j1111, composer3, 0);
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
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i113 >> 3) & 14) | 24960, 10);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2 = composerStartRestartGroup;
                modifier3 = modifier16;
                j5 = j1111;
                j6 = j1112;
                f4 = f17;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                } else {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 8) != 0) {
                        containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        indicatorColor = j4;
                    }
                    if ((i2 & 32) != 0) {
                        i7 = i3 & (-458753);
                        f3 = PositionalThreshold;
                    } else {
                        f3 = f2;
                        i7 = i3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
                }
                final long j1113 = indicatorColor;
                Modifier modifier17 = companion;
                int i114 = i7;
                Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default14 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier17, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
                float f18 = f3;
                long j1114 = containerColor;
                Alignment center14 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy14 = BoxKt.maybeCachedBoxMeasurePolicy(center14, false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default14);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy14, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap14, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier14, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance14 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
                CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                        invoke(bool.booleanValue(), composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2, Composer composer3, int i115) {
                        ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                        if ((i115 & 6) == 0) {
                            i115 |= composer3.changed(z2) ? 4 : 2;
                        }
                        if ((i115 & 19) != 18 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(167807595, i115, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                            }
                            if (z2) {
                                composer3.startReplaceGroup(576835739);
                                ComposerKt.sourceInformation(composer3, "456@17267L201");
                                ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j1113, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                                composer3.endReplaceGroup();
                            } else {
                                composer3.startReplaceGroup(577079337);
                                ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                                ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                                boolean zChanged = composer3.changed(pullToRefreshState);
                                final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Float invoke() {
                                            return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j1113, composer3, 0);
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
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i114 >> 3) & 14) | 24960, 10);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2 = composerStartRestartGroup;
                modifier3 = modifier17;
                j5 = j1113;
                j6 = j1114;
                f4 = f18;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$2
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
                        this.$tmp0_rcvr.m2732Indicator2poqoh4(pullToRefreshState, z, modifier3, j6, j5, f4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 1572864;
        if ((599187 & i3) == 599186) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 8) != 0) {
                    containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -7169;
                } else {
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    indicatorColor = j4;
                }
                if ((i2 & 32) != 0) {
                    i7 = i3 & (-458753);
                    f3 = PositionalThreshold;
                } else {
                    f3 = f2;
                    i7 = i3;
                }
            } else {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 8) != 0) {
                    containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -7169;
                } else {
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    indicatorColor = j4;
                }
                if ((i2 & 32) != 0) {
                    i7 = i3 & (-458753);
                    f3 = PositionalThreshold;
                } else {
                    f3 = f2;
                    i7 = i3;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
            }
            final long j1115 = indicatorColor;
            Modifier modifier18 = companion;
            int i115 = i7;
            Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default15 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier18, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
            float f19 = f3;
            long j1116 = containerColor;
            Alignment center15 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy15 = BoxKt.maybeCachedBoxMeasurePolicy(center15, false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default15);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy15, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap15, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3454constructorimpl.getInserting()) {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier15, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance15 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
            CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                    invoke(bool.booleanValue(), composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z2, Composer composer3, int i116) {
                    ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                    if ((i116 & 6) == 0) {
                        i116 |= composer3.changed(z2) ? 4 : 2;
                    }
                    if ((i116 & 19) != 18 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(167807595, i116, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                        }
                        if (z2) {
                            composer3.startReplaceGroup(576835739);
                            ComposerKt.sourceInformation(composer3, "456@17267L201");
                            ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j1115, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                            composer3.endReplaceGroup();
                        } else {
                            composer3.startReplaceGroup(577079337);
                            ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                            ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                            boolean zChanged = composer3.changed(pullToRefreshState);
                            final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                            Object objRememberedValue = composer3.rememberedValue();
                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j1115, composer3, 0);
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
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i115 >> 3) & 14) | 24960, 10);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer2 = composerStartRestartGroup;
            modifier3 = modifier18;
            j5 = j1115;
            j6 = j1116;
            f4 = f19;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 8) != 0) {
                    containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -7169;
                } else {
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    indicatorColor = j4;
                }
                if ((i2 & 32) != 0) {
                    i7 = i3 & (-458753);
                    f3 = PositionalThreshold;
                } else {
                    f3 = f2;
                    i7 = i3;
                }
            } else {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 8) != 0) {
                    containerColor = INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -7169;
                } else {
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    indicatorColor = INSTANCE.getIndicatorColor(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    indicatorColor = j4;
                }
                if ((i2 & 32) != 0) {
                    i7 = i3 & (-458753);
                    f3 = PositionalThreshold;
                } else {
                    f3 = f2;
                    i7 = i3;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1076870256, i7, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:440)");
            }
            final long j1117 = indicatorColor;
            Modifier modifier19 = companion;
            int i116 = i7;
            Modifier modifierM2748pullToRefreshIndicatorwUdLESc$default16 = PullToRefreshKt.m2748pullToRefreshIndicatorwUdLESc$default(modifier19, pullToRefreshState, z, f3, null, containerColor, 0.0f, 40, null);
            float f110 = f3;
            long j1118 = containerColor;
            Alignment center16 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy16 = BoxKt.maybeCachedBoxMeasurePolicy(center16, false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM2748pullToRefreshIndicatorwUdLESc$default16);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy16, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap16, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3454constructorimpl.getInserting()) {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier16, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance16 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 396611011, "C454@17197L504,451@17052L649:PullToRefresh.kt#djiw08");
            CrossfadeKt.Crossfade(Boolean.valueOf(z), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(167807595, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer3, Integer num) {
                    invoke(bool.booleanValue(), composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z2, Composer composer3, int i117) {
                    ComposerKt.sourceInformation(composer3, "C:PullToRefresh.kt#djiw08");
                    if ((i117 & 6) == 0) {
                        i117 |= composer3.changed(z2) ? 4 : 2;
                    }
                    if ((i117 & 19) != 18 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(167807595, i117, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:455)");
                        }
                        if (z2) {
                            composer3.startReplaceGroup(576835739);
                            ComposerKt.sourceInformation(composer3, "456@17267L201");
                            ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(SizeKt.m765size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), j1117, PullToRefreshKt.StrokeWidth, 0L, 0, composer3, 390, 24);
                            composer3.endReplaceGroup();
                        } else {
                            composer3.startReplaceGroup(577079337);
                            ComposerKt.sourceInformation(composer3, "463@17581L26,462@17514L155");
                            ComposerKt.sourceInformationMarkerStart(composer3, -1505402512, "CC(remember):PullToRefresh.kt#9igjgp");
                            boolean zChanged = composer3.changed(pullToRefreshState);
                            final PullToRefreshState pullToRefreshState2 = pullToRefreshState;
                            Object objRememberedValue = composer3.rememberedValue();
                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$1$1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(pullToRefreshState2.getDistanceFraction());
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            PullToRefreshKt.m2739CircularArrowProgressIndicatorRPmYEkk((Function0) objRememberedValue, j1117, composer3, 0);
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
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i116 >> 3) & 14) | 24960, 10);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer2 = composerStartRestartGroup;
            modifier3 = modifier19;
            j5 = j1117;
            j6 = j1118;
            f4 = f110;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$2
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
                    this.$tmp0_rcvr.m2732Indicator2poqoh4(pullToRefreshState, z, modifier3, j6, j5, f4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }
}
