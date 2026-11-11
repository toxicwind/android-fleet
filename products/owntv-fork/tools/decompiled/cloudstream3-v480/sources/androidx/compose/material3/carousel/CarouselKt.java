package androidx.compose.material3.carousel;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.pager.PagerKt;
import androidx.compose.foundation.pager.PagerScope;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Carousel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a»\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000526\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u001621\u0010\u0017\u001a-\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0091\u0001\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00142\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\u00142\b\b\u0002\u0010\u000e\u001a\u00020\u000f21\u0010\u0017\u001a-\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a}\u0010$\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00142\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u000e\u001a\u00020\u000f21\u0010\u0017\u001a-\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a\u0018\u0010(\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0000\u001a\u0018\u0010+\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0001\u001a \u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020\bH\u0002\u001a\u0019\u00101\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u00102\u001a\u0019\u00103\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u00102\u001a:\u00104\u001a\u00020\u0013*\u00020\u00132\u0006\u00105\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006;"}, d2 = {"Carousel", "", "state", "Landroidx/compose/material3/carousel/CarouselState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "keylineList", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "availableSpace", "itemSpacing", "Landroidx/compose/material3/carousel/KeylineList;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "maxNonFocalVisibleItemCount", "", "modifier", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/Dp;", "flingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "content", "Landroidx/compose/material3/carousel/CarouselItemScope;", "itemIndex", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "Carousel-V-95POc", "(Landroidx/compose/material3/carousel/CarouselState;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;ILandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "HorizontalMultiBrowseCarousel", "preferredItemWidth", "minSmallItemWidth", "maxSmallItemWidth", "HorizontalMultiBrowseCarousel-zCIJ0Nk", "(Landroidx/compose/material3/carousel/CarouselState;FLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;FFLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "HorizontalUncontainedCarousel", "itemWidth", "HorizontalUncontainedCarousel-9QcgTRs", "(Landroidx/compose/material3/carousel/CarouselState;FLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "calculateCurrentScrollOffset", "strategy", "Landroidx/compose/material3/carousel/Strategy;", "calculateMaxScrollOffset", "getProgress", TtmlNode.ANNOTATION_POSITION_BEFORE, "Landroidx/compose/material3/carousel/Keyline;", TtmlNode.ANNOTATION_POSITION_AFTER, "unadjustedOffset", "calculateAfterContentPadding", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/runtime/Composer;I)F", "calculateBeforeContentPadding", "carouselItem", "index", "Lkotlin/Function0;", "carouselItemInfo", "Landroidx/compose/material3/carousel/CarouselItemInfoImpl;", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CarouselKt {
    /* JADX WARN: Code duplicated, block: B:100:0x011f  */
    /* JADX WARN: Code duplicated, block: B:105:0x0130  */
    /* JADX WARN: Code duplicated, block: B:107:0x013a  */
    /* JADX WARN: Code duplicated, block: B:114:0x0154 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:115:0x0156  */
    /* JADX WARN: Code duplicated, block: B:116:0x015d  */
    /* JADX WARN: Code duplicated, block: B:119:0x0163  */
    /* JADX WARN: Code duplicated, block: B:120:0x016a  */
    /* JADX WARN: Code duplicated, block: B:123:0x0170  */
    /* JADX WARN: Code duplicated, block: B:124:0x0183  */
    /* JADX WARN: Code duplicated, block: B:126:0x0187  */
    /* JADX WARN: Code duplicated, block: B:128:0x0190  */
    /* JADX WARN: Code duplicated, block: B:130:0x0199  */
    /* JADX WARN: Code duplicated, block: B:131:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:135:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:138:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:139:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:142:0x0201  */
    /* JADX WARN: Code duplicated, block: B:143:0x0204  */
    /* JADX WARN: Code duplicated, block: B:146:0x020f  */
    /* JADX WARN: Code duplicated, block: B:149:0x0219  */
    /* JADX WARN: Code duplicated, block: B:151:0x0221  */
    /* JADX WARN: Code duplicated, block: B:154:0x026b  */
    /* JADX WARN: Code duplicated, block: B:158:0x027a  */
    /* JADX WARN: Code duplicated, block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0066  */
    /* JADX WARN: Code duplicated, block: B:37:0x0069  */
    /* JADX WARN: Code duplicated, block: B:39:0x006d  */
    /* JADX WARN: Code duplicated, block: B:41:0x0075  */
    /* JADX WARN: Code duplicated, block: B:42:0x0078  */
    /* JADX WARN: Code duplicated, block: B:47:0x0082  */
    /* JADX WARN: Code duplicated, block: B:49:0x0086  */
    /* JADX WARN: Code duplicated, block: B:51:0x008e  */
    /* JADX WARN: Code duplicated, block: B:52:0x0091  */
    /* JADX WARN: Code duplicated, block: B:55:0x0097  */
    /* JADX WARN: Code duplicated, block: B:58:0x009f  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:64:0x00af  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:79:0x00da  */
    /* JADX WARN: Code duplicated, block: B:80:0x00df  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:84:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:85:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:89:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:90:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:92:0x0103  */
    /* JADX WARN: Code duplicated, block: B:94:0x010b  */
    /* JADX WARN: Code duplicated, block: B:95:0x010e  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: HorizontalMultiBrowseCarousel-zCIJ0Nk, reason: not valid java name */
    public static final void m2602HorizontalMultiBrowseCarouselzCIJ0Nk(final CarouselState carouselState, final float f, Modifier modifier, float f2, TargetedFlingBehavior targetedFlingBehavior, float f3, float f4, PaddingValues paddingValues, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i, final int i2) {
        int i3;
        float f5;
        final Modifier modifier2;
        int i4;
        float f6;
        int i5;
        TargetedFlingBehavior targetedFlingBehaviorSingleAdvanceFlingBehavior;
        int i6;
        float fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
        int i7;
        int i8;
        float fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
        int i9;
        int i10;
        PaddingValues paddingValues2;
        int i11;
        int i12;
        int i13;
        Modifier modifier3;
        float fM6890constructorimpl;
        CarouselState carouselState2;
        PaddingValues paddingValuesM713PaddingValues0680j_4;
        TargetedFlingBehavior targetedFlingBehavior2;
        int i14;
        Modifier modifier4;
        float f7;
        final Density density;
        int i15;
        boolean z;
        boolean z2;
        boolean z3;
        Object objRememberedValue;
        final PaddingValues paddingValues3;
        final float f8;
        final TargetedFlingBehavior targetedFlingBehavior3;
        final float f9;
        final float f10;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1825706865);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(HorizontalMultiBrowseCarousel)P(8,7:c#ui.unit.Dp,6,3:c#ui.unit.Dp,2,5:c#ui.unit.Dp,4:c#ui.unit.Dp,1)106@5374L41,112@5703L7,116@5816L554,113@5715L1048:Carousel.kt#dcf9yb");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(carouselState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
            f5 = f;
        } else {
            f5 = f;
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changed(f5) ? 32 : 16;
            }
        }
        int i16 = i2 & 4;
        if (i16 == 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    f6 = f2;
                    if (composerStartRestartGroup.changed(f6)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
                        int i17 = composerStartRestartGroup.changed(targetedFlingBehaviorSingleAdvanceFlingBehavior) ? 16384 : 8192;
                        i3 |= i17;
                    } else {
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
                    }
                    i3 |= i17;
                } else {
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = f3;
                        if (composerStartRestartGroup.changed(fM2599getMinSmallItemSizeD9Ej5fM$material3_release)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        i3 |= 1572864;
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                    } else {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                        if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changed(fM2598getMaxSmallItemSizeD9Ej5fM$material3_release)) {
                                i9 = 1048576;
                            } else {
                                i9 = 524288;
                            }
                            i3 |= i9;
                        }
                    }
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                        paddingValues2 = paddingValues;
                    } else {
                        paddingValues2 = paddingValues;
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(paddingValues2)) {
                                i11 = 8388608;
                            } else {
                                i11 = 4194304;
                            }
                            i3 |= i11;
                        }
                    }
                    i12 = i3;
                    if ((i2 & 256) != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i13 = 67108864;
                            } else {
                                i13 = 33554432;
                            }
                            i12 |= i13;
                        }
                        if ((i12 & 38347923) == 38347922 || !composerStartRestartGroup.getSkipping()) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i16 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier2;
                                }
                                if (i4 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f6;
                                }
                                if ((i2 & 16) != 0) {
                                    CarouselDefaults carouselDefaults = CarouselDefaults.INSTANCE;
                                    int i18 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                    carouselState2 = carouselState;
                                    i12 &= -57345;
                                    targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i18, 2);
                                } else {
                                    carouselState2 = carouselState;
                                }
                                if (i6 != 0) {
                                    fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                                }
                                if (i8 != 0) {
                                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                                }
                                if (i10 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                } else {
                                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                }
                                targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                                i14 = i12;
                                modifier4 = modifier3;
                                f7 = fM6890constructorimpl;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 16) != 0) {
                                    i12 &= -57345;
                                }
                                float f11 = f6;
                                modifier4 = modifier2;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                                f7 = f11;
                                carouselState2 = carouselState;
                                targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                                i14 = i12;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                            }
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume = composerStartRestartGroup.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            density = (Density) objConsume;
                            Orientation orientation = Orientation.Horizontal;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                            boolean zChanged = composerStartRestartGroup.changed(density);
                            i15 = i14;
                            if ((i14 & 112) == 32) {
                                z = true;
                            } else {
                                z = false;
                            }
                            boolean zChangedInstance = z | zChanged | composerStartRestartGroup.changedInstance(carouselState2);
                            if ((i15 & 458752) == 131072) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            z3 = zChangedInstance | z2 | ((i15 & 3670016) == 1048576);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                final CarouselState carouselState3 = carouselState2;
                                final float f12 = f5;
                                final float f13 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                                final float f14 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f15, Float f16) {
                                        return invoke(f15.floatValue(), f16.floatValue());
                                    }

                                    public final KeylineList invoke(float f15, float f16) {
                                        Density density2 = density;
                                        return KeylinesKt.multiBrowseKeylineList(density2, f15, density2.mo406toPx0680j_4(f12), f16, carouselState3.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f14), density2.mo406toPx0680j_4(f13));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i19 = i15 << 9;
                            m2601CarouselV95POc(carouselState, orientation, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i19) | (3670016 & i19) | (i19 & 29360128) | (i15 & 234881024), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            modifier2 = modifier4;
                            f8 = f7;
                            targetedFlingBehavior3 = targetedFlingBehavior2;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            f8 = f6;
                            paddingValues3 = paddingValues2;
                            targetedFlingBehavior3 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        }
                        f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                                public final void invoke(Composer composer2, int i20) {
                                    CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                        }
                    }
                    i12 |= 100663296;
                    if ((i12 & 38347923) == 38347922) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults2 = CarouselDefaults.INSTANCE;
                                int i110 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults2.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i110, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        } else {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults3 = CarouselDefaults.INSTANCE;
                                int i111 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults3.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                        }
                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = composerStartRestartGroup.consume(localDensity2);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume2;
                        Orientation orientation2 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged2 = composerStartRestartGroup.changed(density);
                        i15 = i14;
                        if ((i14 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean zChangedInstance2 = z | zChanged2 | composerStartRestartGroup.changedInstance(carouselState2);
                        if ((i15 & 458752) == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = zChangedInstance2 | z2 | ((i15 & 3670016) == 1048576);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            final CarouselState carouselState4 = carouselState2;
                            final float f15 = f5;
                            final float f16 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f17 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f18, Float f19) {
                                    return invoke(f18.floatValue(), f19.floatValue());
                                }

                                public final KeylineList invoke(float f18, float f19) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f18, density2.mo406toPx0680j_4(f15), f19, carouselState4.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f17), density2.mo406toPx0680j_4(f16));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            final CarouselState carouselState5 = carouselState2;
                            final float f18 = f5;
                            final float f19 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f110 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f111, Float f112) {
                                    return invoke(f111.floatValue(), f112.floatValue());
                                }

                                public final KeylineList invoke(float f111, float f112) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f111, density2.mo406toPx0680j_4(f18), f112, carouselState5.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f110), density2.mo406toPx0680j_4(f19));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i112 = i15 << 9;
                        m2601CarouselV95POc(carouselState, orientation2, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i112) | (3670016 & i112) | (i112 & 29360128) | (i15 & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier2 = modifier4;
                        f8 = f7;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults4 = CarouselDefaults.INSTANCE;
                                int i113 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults4.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i113, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        } else {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults5 = CarouselDefaults.INSTANCE;
                                int i114 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults5.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i114, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                        }
                        ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = composerStartRestartGroup.consume(localDensity3);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume3;
                        Orientation orientation3 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged3 = composerStartRestartGroup.changed(density);
                        i15 = i14;
                        if ((i14 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean zChangedInstance3 = z | zChanged3 | composerStartRestartGroup.changedInstance(carouselState2);
                        if ((i15 & 458752) == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = zChangedInstance3 | z2 | ((i15 & 3670016) == 1048576);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            final CarouselState carouselState6 = carouselState2;
                            final float f111 = f5;
                            final float f112 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f113 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f114, Float f115) {
                                    return invoke(f114.floatValue(), f115.floatValue());
                                }

                                public final KeylineList invoke(float f114, float f115) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f114, density2.mo406toPx0680j_4(f111), f115, carouselState6.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f113), density2.mo406toPx0680j_4(f112));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            final CarouselState carouselState7 = carouselState2;
                            final float f114 = f5;
                            final float f115 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f116 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f117, Float f118) {
                                    return invoke(f117.floatValue(), f118.floatValue());
                                }

                                public final KeylineList invoke(float f117, float f118) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f117, density2.mo406toPx0680j_4(f114), f118, carouselState7.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f116), density2.mo406toPx0680j_4(f115));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i115 = i15 << 9;
                        m2601CarouselV95POc(carouselState, orientation3, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i115) | (3670016 & i115) | (i115 & 29360128) | (i15 & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier2 = modifier4;
                        f8 = f7;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    }
                    f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                            public final void invoke(Composer composer2, int i20) {
                                CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = f3;
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                } else {
                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(fM2598getMaxSmallItemSizeD9Ej5fM$material3_release)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                    paddingValues2 = paddingValues;
                } else {
                    paddingValues2 = paddingValues;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(paddingValues2)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i3;
                if ((i2 & 256) != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i13 = 67108864;
                        } else {
                            i13 = 33554432;
                        }
                        i12 |= i13;
                    }
                    if ((i12 & 38347923) == 38347922) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults6 = CarouselDefaults.INSTANCE;
                                int i116 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults6.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i116, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        } else {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults7 = CarouselDefaults.INSTANCE;
                                int i117 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults7.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i117, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                        }
                        ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = composerStartRestartGroup.consume(localDensity4);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume4;
                        Orientation orientation4 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged4 = composerStartRestartGroup.changed(density);
                        i15 = i14;
                        if ((i14 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean zChangedInstance4 = z | zChanged4 | composerStartRestartGroup.changedInstance(carouselState2);
                        if ((i15 & 458752) == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = zChangedInstance4 | z2 | ((i15 & 3670016) == 1048576);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            final CarouselState carouselState8 = carouselState2;
                            final float f117 = f5;
                            final float f118 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f119 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f1110, Float f1111) {
                                    return invoke(f1110.floatValue(), f1111.floatValue());
                                }

                                public final KeylineList invoke(float f1110, float f1111) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f1110, density2.mo406toPx0680j_4(f117), f1111, carouselState8.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f119), density2.mo406toPx0680j_4(f118));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            final CarouselState carouselState9 = carouselState2;
                            final float f1110 = f5;
                            final float f1111 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f1112 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f1113, Float f1114) {
                                    return invoke(f1113.floatValue(), f1114.floatValue());
                                }

                                public final KeylineList invoke(float f1113, float f1114) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f1113, density2.mo406toPx0680j_4(f1110), f1114, carouselState9.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1112), density2.mo406toPx0680j_4(f1111));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i118 = i15 << 9;
                        m2601CarouselV95POc(carouselState, orientation4, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i118) | (3670016 & i118) | (i118 & 29360128) | (i15 & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier2 = modifier4;
                        f8 = f7;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults8 = CarouselDefaults.INSTANCE;
                                int i119 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults8.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i119, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        } else {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults9 = CarouselDefaults.INSTANCE;
                                int i1110 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults9.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1110, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                        }
                        ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume5 = composerStartRestartGroup.consume(localDensity5);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume5;
                        Orientation orientation5 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged5 = composerStartRestartGroup.changed(density);
                        i15 = i14;
                        if ((i14 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean zChangedInstance5 = z | zChanged5 | composerStartRestartGroup.changedInstance(carouselState2);
                        if ((i15 & 458752) == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = zChangedInstance5 | z2 | ((i15 & 3670016) == 1048576);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            final CarouselState carouselState10 = carouselState2;
                            final float f1113 = f5;
                            final float f1114 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f1115 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f1116, Float f1117) {
                                    return invoke(f1116.floatValue(), f1117.floatValue());
                                }

                                public final KeylineList invoke(float f1116, float f1117) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f1116, density2.mo406toPx0680j_4(f1113), f1117, carouselState10.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1115), density2.mo406toPx0680j_4(f1114));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            final CarouselState carouselState11 = carouselState2;
                            final float f1116 = f5;
                            final float f1117 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f1118 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f1119, Float f11110) {
                                    return invoke(f1119.floatValue(), f11110.floatValue());
                                }

                                public final KeylineList invoke(float f1119, float f11110) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f1119, density2.mo406toPx0680j_4(f1116), f11110, carouselState11.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1118), density2.mo406toPx0680j_4(f1117));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i1111 = i15 << 9;
                        m2601CarouselV95POc(carouselState, orientation5, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i1111) | (3670016 & i1111) | (i1111 & 29360128) | (i15 & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier2 = modifier4;
                        f8 = f7;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    }
                    f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                            public final void invoke(Composer composer2, int i20) {
                                CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i12 |= 100663296;
                if ((i12 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults10 = CarouselDefaults.INSTANCE;
                            int i1112 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults10.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1112, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults11 = CarouselDefaults.INSTANCE;
                            int i1113 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1113, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume6 = composerStartRestartGroup.consume(localDensity6);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume6;
                    Orientation orientation6 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged6 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance6 = z | zChanged6 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance6 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState12 = carouselState2;
                        final float f1119 = f5;
                        final float f11110 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f11111 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f11112, Float f11113) {
                                return invoke(f11112.floatValue(), f11113.floatValue());
                            }

                            public final KeylineList invoke(float f11112, float f11113) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f11112, density2.mo406toPx0680j_4(f1119), f11113, carouselState12.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111), density2.mo406toPx0680j_4(f11110));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState13 = carouselState2;
                        final float f11112 = f5;
                        final float f11113 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f11114 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f11115, Float f11116) {
                                return invoke(f11115.floatValue(), f11116.floatValue());
                            }

                            public final KeylineList invoke(float f11115, float f11116) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f11115, density2.mo406toPx0680j_4(f11112), f11116, carouselState13.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11114), density2.mo406toPx0680j_4(f11113));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i1114 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation6, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i1114) | (3670016 & i1114) | (i1114 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults12 = CarouselDefaults.INSTANCE;
                            int i1115 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults12.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1115, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults13 = CarouselDefaults.INSTANCE;
                            int i1116 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults13.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1116, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume7 = composerStartRestartGroup.consume(localDensity7);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume7;
                    Orientation orientation7 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged7 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance7 = z | zChanged7 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance7 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState14 = carouselState2;
                        final float f11115 = f5;
                        final float f11116 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f11117 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f11118, Float f11119) {
                                return invoke(f11118.floatValue(), f11119.floatValue());
                            }

                            public final KeylineList invoke(float f11118, float f11119) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f11118, density2.mo406toPx0680j_4(f11115), f11119, carouselState14.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11117), density2.mo406toPx0680j_4(f11116));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState15 = carouselState2;
                        final float f11118 = f5;
                        final float f11119 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f111110 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111, Float f111112) {
                                return invoke(f111111.floatValue(), f111112.floatValue());
                            }

                            public final KeylineList invoke(float f111111, float f111112) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f111111, density2.mo406toPx0680j_4(f11118), f111112, carouselState15.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111110), density2.mo406toPx0680j_4(f11119));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i1117 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation7, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i1117) | (3670016 & i1117) | (i1117 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                        public final void invoke(Composer composer2, int i20) {
                            CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            f6 = f2;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
                    if (composerStartRestartGroup.changed(targetedFlingBehaviorSingleAdvanceFlingBehavior)) {
                    }
                    i3 |= i17;
                } else {
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
                }
                i3 |= i17;
            } else {
                targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    fM2599getMinSmallItemSizeD9Ej5fM$material3_release = f3;
                    if (composerStartRestartGroup.changed(fM2599getMinSmallItemSizeD9Ej5fM$material3_release)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                } else {
                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(fM2598getMaxSmallItemSizeD9Ej5fM$material3_release)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                    paddingValues2 = paddingValues;
                } else {
                    paddingValues2 = paddingValues;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(paddingValues2)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i3;
                if ((i2 & 256) != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i13 = 67108864;
                        } else {
                            i13 = 33554432;
                        }
                        i12 |= i13;
                    }
                    if ((i12 & 38347923) == 38347922) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults14 = CarouselDefaults.INSTANCE;
                                int i1118 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults14.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1118, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        } else {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults15 = CarouselDefaults.INSTANCE;
                                int i1119 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults15.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1119, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                        }
                        ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume8 = composerStartRestartGroup.consume(localDensity8);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume8;
                        Orientation orientation8 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged8 = composerStartRestartGroup.changed(density);
                        i15 = i14;
                        if ((i14 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean zChangedInstance8 = z | zChanged8 | composerStartRestartGroup.changedInstance(carouselState2);
                        if ((i15 & 458752) == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = zChangedInstance8 | z2 | ((i15 & 3670016) == 1048576);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            final CarouselState carouselState16 = carouselState2;
                            final float f111111 = f5;
                            final float f111112 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f111113 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f111114, Float f111115) {
                                    return invoke(f111114.floatValue(), f111115.floatValue());
                                }

                                public final KeylineList invoke(float f111114, float f111115) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f111114, density2.mo406toPx0680j_4(f111111), f111115, carouselState16.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111113), density2.mo406toPx0680j_4(f111112));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            final CarouselState carouselState17 = carouselState2;
                            final float f111114 = f5;
                            final float f111115 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f111116 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f111117, Float f111118) {
                                    return invoke(f111117.floatValue(), f111118.floatValue());
                                }

                                public final KeylineList invoke(float f111117, float f111118) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f111117, density2.mo406toPx0680j_4(f111114), f111118, carouselState17.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111116), density2.mo406toPx0680j_4(f111115));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i11110 = i15 << 9;
                        m2601CarouselV95POc(carouselState, orientation8, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11110) | (3670016 & i11110) | (i11110 & 29360128) | (i15 & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier2 = modifier4;
                        f8 = f7;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults16 = CarouselDefaults.INSTANCE;
                                int i11111 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults16.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        } else {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults17 = CarouselDefaults.INSTANCE;
                                int i11112 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults17.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11112, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                        }
                        ProvidableCompositionLocal<Density> localDensity9 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume9 = composerStartRestartGroup.consume(localDensity9);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume9;
                        Orientation orientation9 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged9 = composerStartRestartGroup.changed(density);
                        i15 = i14;
                        if ((i14 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean zChangedInstance9 = z | zChanged9 | composerStartRestartGroup.changedInstance(carouselState2);
                        if ((i15 & 458752) == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = zChangedInstance9 | z2 | ((i15 & 3670016) == 1048576);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            final CarouselState carouselState18 = carouselState2;
                            final float f111117 = f5;
                            final float f111118 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f111119 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111110, Float f1111111) {
                                    return invoke(f1111110.floatValue(), f1111111.floatValue());
                                }

                                public final KeylineList invoke(float f1111110, float f1111111) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f1111110, density2.mo406toPx0680j_4(f111117), f1111111, carouselState18.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111119), density2.mo406toPx0680j_4(f111118));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            final CarouselState carouselState19 = carouselState2;
                            final float f1111110 = f5;
                            final float f1111111 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f1111112 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111113, Float f1111114) {
                                    return invoke(f1111113.floatValue(), f1111114.floatValue());
                                }

                                public final KeylineList invoke(float f1111113, float f1111114) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f1111113, density2.mo406toPx0680j_4(f1111110), f1111114, carouselState19.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111112), density2.mo406toPx0680j_4(f1111111));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i11113 = i15 << 9;
                        m2601CarouselV95POc(carouselState, orientation9, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11113) | (3670016 & i11113) | (i11113 & 29360128) | (i15 & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier2 = modifier4;
                        f8 = f7;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    }
                    f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                            public final void invoke(Composer composer2, int i20) {
                                CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i12 |= 100663296;
                if ((i12 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults18 = CarouselDefaults.INSTANCE;
                            int i11114 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults18.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11114, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults19 = CarouselDefaults.INSTANCE;
                            int i11115 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults19.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11115, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity10 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume10 = composerStartRestartGroup.consume(localDensity10);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume10;
                    Orientation orientation10 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged10 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance10 = z | zChanged10 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance10 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState110 = carouselState2;
                        final float f1111113 = f5;
                        final float f1111114 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f1111115 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111116, Float f1111117) {
                                return invoke(f1111116.floatValue(), f1111117.floatValue());
                            }

                            public final KeylineList invoke(float f1111116, float f1111117) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f1111116, density2.mo406toPx0680j_4(f1111113), f1111117, carouselState110.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111115), density2.mo406toPx0680j_4(f1111114));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState111 = carouselState2;
                        final float f1111116 = f5;
                        final float f1111117 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f1111118 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111119, Float f11111110) {
                                return invoke(f1111119.floatValue(), f11111110.floatValue());
                            }

                            public final KeylineList invoke(float f1111119, float f11111110) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f1111119, density2.mo406toPx0680j_4(f1111116), f11111110, carouselState111.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111118), density2.mo406toPx0680j_4(f1111117));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i11116 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation10, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11116) | (3670016 & i11116) | (i11116 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults110 = CarouselDefaults.INSTANCE;
                            int i11117 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults110.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11117, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults111 = CarouselDefaults.INSTANCE;
                            int i11118 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11118, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity11 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11 = composerStartRestartGroup.consume(localDensity11);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume11;
                    Orientation orientation11 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged11 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance11 = z | zChanged11 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance11 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState112 = carouselState2;
                        final float f1111119 = f5;
                        final float f11111110 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f11111111 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111112, Float f11111113) {
                                return invoke(f11111112.floatValue(), f11111113.floatValue());
                            }

                            public final KeylineList invoke(float f11111112, float f11111113) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f11111112, density2.mo406toPx0680j_4(f1111119), f11111113, carouselState112.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111), density2.mo406toPx0680j_4(f11111110));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState113 = carouselState2;
                        final float f11111112 = f5;
                        final float f11111113 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f11111114 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111115, Float f11111116) {
                                return invoke(f11111115.floatValue(), f11111116.floatValue());
                            }

                            public final KeylineList invoke(float f11111115, float f11111116) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f11111115, density2.mo406toPx0680j_4(f11111112), f11111116, carouselState113.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111114), density2.mo406toPx0680j_4(f11111113));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i11119 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation11, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11119) | (3670016 & i11119) | (i11119 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                        public final void invoke(Composer composer2, int i20) {
                            CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = f3;
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
            } else {
                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(fM2598getMaxSmallItemSizeD9Ej5fM$material3_release)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
                paddingValues2 = paddingValues;
            } else {
                paddingValues2 = paddingValues;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(paddingValues2)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
            }
            i12 = i3;
            if ((i2 & 256) != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i13 = 67108864;
                    } else {
                        i13 = 33554432;
                    }
                    i12 |= i13;
                }
                if ((i12 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults112 = CarouselDefaults.INSTANCE;
                            int i111110 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults112.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111110, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults113 = CarouselDefaults.INSTANCE;
                            int i111111 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults113.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111111, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity12 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume12 = composerStartRestartGroup.consume(localDensity12);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume12;
                    Orientation orientation12 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged12 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance12 = z | zChanged12 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance12 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState114 = carouselState2;
                        final float f11111115 = f5;
                        final float f11111116 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f11111117 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111118, Float f11111119) {
                                return invoke(f11111118.floatValue(), f11111119.floatValue());
                            }

                            public final KeylineList invoke(float f11111118, float f11111119) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f11111118, density2.mo406toPx0680j_4(f11111115), f11111119, carouselState114.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111117), density2.mo406toPx0680j_4(f11111116));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState115 = carouselState2;
                        final float f11111118 = f5;
                        final float f11111119 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f111111110 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111111, Float f111111112) {
                                return invoke(f111111111.floatValue(), f111111112.floatValue());
                            }

                            public final KeylineList invoke(float f111111111, float f111111112) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f111111111, density2.mo406toPx0680j_4(f11111118), f111111112, carouselState115.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111110), density2.mo406toPx0680j_4(f11111119));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i111112 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation12, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i111112) | (3670016 & i111112) | (i111112 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults114 = CarouselDefaults.INSTANCE;
                            int i111113 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults114.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111113, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults115 = CarouselDefaults.INSTANCE;
                            int i111114 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults115.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111114, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity13 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume13 = composerStartRestartGroup.consume(localDensity13);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume13;
                    Orientation orientation13 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged13 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance13 = z | zChanged13 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance13 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState116 = carouselState2;
                        final float f111111111 = f5;
                        final float f111111112 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f111111113 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111114, Float f111111115) {
                                return invoke(f111111114.floatValue(), f111111115.floatValue());
                            }

                            public final KeylineList invoke(float f111111114, float f111111115) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f111111114, density2.mo406toPx0680j_4(f111111111), f111111115, carouselState116.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111113), density2.mo406toPx0680j_4(f111111112));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState117 = carouselState2;
                        final float f111111114 = f5;
                        final float f111111115 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f111111116 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111117, Float f111111118) {
                                return invoke(f111111117.floatValue(), f111111118.floatValue());
                            }

                            public final KeylineList invoke(float f111111117, float f111111118) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f111111117, density2.mo406toPx0680j_4(f111111114), f111111118, carouselState117.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111116), density2.mo406toPx0680j_4(f111111115));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i111115 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation13, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i111115) | (3670016 & i111115) | (i111115 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                        public final void invoke(Composer composer2, int i20) {
                            CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i12 |= 100663296;
            if ((i12 & 38347923) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults116 = CarouselDefaults.INSTANCE;
                        int i111116 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults116.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111116, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                } else {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults117 = CarouselDefaults.INSTANCE;
                        int i111117 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults117.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111117, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                }
                ProvidableCompositionLocal<Density> localDensity14 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume14 = composerStartRestartGroup.consume(localDensity14);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume14;
                Orientation orientation14 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged14 = composerStartRestartGroup.changed(density);
                i15 = i14;
                if ((i14 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                boolean zChangedInstance14 = z | zChanged14 | composerStartRestartGroup.changedInstance(carouselState2);
                if ((i15 & 458752) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = zChangedInstance14 | z2 | ((i15 & 3670016) == 1048576);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    final CarouselState carouselState118 = carouselState2;
                    final float f111111117 = f5;
                    final float f111111118 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f111111119 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111110, Float f1111111111) {
                            return invoke(f1111111110.floatValue(), f1111111111.floatValue());
                        }

                        public final KeylineList invoke(float f1111111110, float f1111111111) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f1111111110, density2.mo406toPx0680j_4(f111111117), f1111111111, carouselState118.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111119), density2.mo406toPx0680j_4(f111111118));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    final CarouselState carouselState119 = carouselState2;
                    final float f1111111110 = f5;
                    final float f1111111111 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f1111111112 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111113, Float f1111111114) {
                            return invoke(f1111111113.floatValue(), f1111111114.floatValue());
                        }

                        public final KeylineList invoke(float f1111111113, float f1111111114) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f1111111113, density2.mo406toPx0680j_4(f1111111110), f1111111114, carouselState119.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111112), density2.mo406toPx0680j_4(f1111111111));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i111118 = i15 << 9;
                m2601CarouselV95POc(carouselState, orientation14, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i111118) | (3670016 & i111118) | (i111118 & 29360128) | (i15 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier2 = modifier4;
                f8 = f7;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults118 = CarouselDefaults.INSTANCE;
                        int i111119 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults118.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111119, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                } else {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults119 = CarouselDefaults.INSTANCE;
                        int i1111110 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults119.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111110, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                }
                ProvidableCompositionLocal<Density> localDensity15 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume15 = composerStartRestartGroup.consume(localDensity15);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume15;
                Orientation orientation15 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged15 = composerStartRestartGroup.changed(density);
                i15 = i14;
                if ((i14 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                boolean zChangedInstance15 = z | zChanged15 | composerStartRestartGroup.changedInstance(carouselState2);
                if ((i15 & 458752) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = zChangedInstance15 | z2 | ((i15 & 3670016) == 1048576);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    final CarouselState carouselState1110 = carouselState2;
                    final float f1111111113 = f5;
                    final float f1111111114 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f1111111115 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111116, Float f1111111117) {
                            return invoke(f1111111116.floatValue(), f1111111117.floatValue());
                        }

                        public final KeylineList invoke(float f1111111116, float f1111111117) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f1111111116, density2.mo406toPx0680j_4(f1111111113), f1111111117, carouselState1110.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111115), density2.mo406toPx0680j_4(f1111111114));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    final CarouselState carouselState1111 = carouselState2;
                    final float f1111111116 = f5;
                    final float f1111111117 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f1111111118 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111119, Float f11111111110) {
                            return invoke(f1111111119.floatValue(), f11111111110.floatValue());
                        }

                        public final KeylineList invoke(float f1111111119, float f11111111110) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f1111111119, density2.mo406toPx0680j_4(f1111111116), f11111111110, carouselState1111.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111118), density2.mo406toPx0680j_4(f1111111117));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i1111111 = i15 << 9;
                m2601CarouselV95POc(carouselState, orientation15, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i1111111) | (3670016 & i1111111) | (i1111111 & 29360128) | (i15 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier2 = modifier4;
                f8 = f7;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            }
            f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
            f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                    public final void invoke(Composer composer2, int i20) {
                        CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                f6 = f2;
                if (composerStartRestartGroup.changed(f6)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
                    if (composerStartRestartGroup.changed(targetedFlingBehaviorSingleAdvanceFlingBehavior)) {
                    }
                    i3 |= i17;
                } else {
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
                }
                i3 |= i17;
            } else {
                targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    fM2599getMinSmallItemSizeD9Ej5fM$material3_release = f3;
                    if (composerStartRestartGroup.changed(fM2599getMinSmallItemSizeD9Ej5fM$material3_release)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                } else {
                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(fM2598getMaxSmallItemSizeD9Ej5fM$material3_release)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                    paddingValues2 = paddingValues;
                } else {
                    paddingValues2 = paddingValues;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(paddingValues2)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i3;
                if ((i2 & 256) != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i13 = 67108864;
                        } else {
                            i13 = 33554432;
                        }
                        i12 |= i13;
                    }
                    if ((i12 & 38347923) == 38347922) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults1110 = CarouselDefaults.INSTANCE;
                                int i1111112 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1110.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111112, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        } else {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults1111 = CarouselDefaults.INSTANCE;
                                int i1111113 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1111.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111113, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                        }
                        ProvidableCompositionLocal<Density> localDensity16 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume16 = composerStartRestartGroup.consume(localDensity16);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume16;
                        Orientation orientation16 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged16 = composerStartRestartGroup.changed(density);
                        i15 = i14;
                        if ((i14 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean zChangedInstance16 = z | zChanged16 | composerStartRestartGroup.changedInstance(carouselState2);
                        if ((i15 & 458752) == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = zChangedInstance16 | z2 | ((i15 & 3670016) == 1048576);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            final CarouselState carouselState1112 = carouselState2;
                            final float f1111111119 = f5;
                            final float f11111111110 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f11111111111 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111112, Float f11111111113) {
                                    return invoke(f11111111112.floatValue(), f11111111113.floatValue());
                                }

                                public final KeylineList invoke(float f11111111112, float f11111111113) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f11111111112, density2.mo406toPx0680j_4(f1111111119), f11111111113, carouselState1112.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111111), density2.mo406toPx0680j_4(f11111111110));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            final CarouselState carouselState1113 = carouselState2;
                            final float f11111111112 = f5;
                            final float f11111111113 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f11111111114 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111115, Float f11111111116) {
                                    return invoke(f11111111115.floatValue(), f11111111116.floatValue());
                                }

                                public final KeylineList invoke(float f11111111115, float f11111111116) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f11111111115, density2.mo406toPx0680j_4(f11111111112), f11111111116, carouselState1113.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111114), density2.mo406toPx0680j_4(f11111111113));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i1111114 = i15 << 9;
                        m2601CarouselV95POc(carouselState, orientation16, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i1111114) | (3670016 & i1111114) | (i1111114 & 29360128) | (i15 & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier2 = modifier4;
                        f8 = f7;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults1112 = CarouselDefaults.INSTANCE;
                                int i1111115 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1112.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111115, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        } else {
                            if (i16 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f6;
                            }
                            if ((i2 & 16) != 0) {
                                CarouselDefaults carouselDefaults1113 = CarouselDefaults.INSTANCE;
                                int i1111116 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                                carouselState2 = carouselState;
                                i12 &= -57345;
                                targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1113.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111116, 2);
                            } else {
                                carouselState2 = carouselState;
                            }
                            if (i6 != 0) {
                                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i8 != 0) {
                                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                            }
                            if (i10 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            } else {
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                            i14 = i12;
                            modifier4 = modifier3;
                            f7 = fM6890constructorimpl;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                        }
                        ProvidableCompositionLocal<Density> localDensity17 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume17 = composerStartRestartGroup.consume(localDensity17);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume17;
                        Orientation orientation17 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged17 = composerStartRestartGroup.changed(density);
                        i15 = i14;
                        if ((i14 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean zChangedInstance17 = z | zChanged17 | composerStartRestartGroup.changedInstance(carouselState2);
                        if ((i15 & 458752) == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = zChangedInstance17 | z2 | ((i15 & 3670016) == 1048576);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            final CarouselState carouselState1114 = carouselState2;
                            final float f11111111115 = f5;
                            final float f11111111116 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f11111111117 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111118, Float f11111111119) {
                                    return invoke(f11111111118.floatValue(), f11111111119.floatValue());
                                }

                                public final KeylineList invoke(float f11111111118, float f11111111119) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f11111111118, density2.mo406toPx0680j_4(f11111111115), f11111111119, carouselState1114.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111117), density2.mo406toPx0680j_4(f11111111116));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            final CarouselState carouselState1115 = carouselState2;
                            final float f11111111118 = f5;
                            final float f11111111119 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                            final float f111111111110 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111111111, Float f111111111112) {
                                    return invoke(f111111111111.floatValue(), f111111111112.floatValue());
                                }

                                public final KeylineList invoke(float f111111111111, float f111111111112) {
                                    Density density2 = density;
                                    return KeylinesKt.multiBrowseKeylineList(density2, f111111111111, density2.mo406toPx0680j_4(f11111111118), f111111111112, carouselState1115.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111110), density2.mo406toPx0680j_4(f11111111119));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i1111117 = i15 << 9;
                        m2601CarouselV95POc(carouselState, orientation17, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i1111117) | (3670016 & i1111117) | (i1111117 & 29360128) | (i15 & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier2 = modifier4;
                        f8 = f7;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    }
                    f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                            public final void invoke(Composer composer2, int i20) {
                                CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i12 |= 100663296;
                if ((i12 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults1114 = CarouselDefaults.INSTANCE;
                            int i1111118 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1114.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111118, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults1115 = CarouselDefaults.INSTANCE;
                            int i1111119 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1115.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111119, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity18 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume18 = composerStartRestartGroup.consume(localDensity18);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume18;
                    Orientation orientation18 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged18 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance18 = z | zChanged18 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance18 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState1116 = carouselState2;
                        final float f111111111111 = f5;
                        final float f111111111112 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f111111111113 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111111114, Float f111111111115) {
                                return invoke(f111111111114.floatValue(), f111111111115.floatValue());
                            }

                            public final KeylineList invoke(float f111111111114, float f111111111115) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f111111111114, density2.mo406toPx0680j_4(f111111111111), f111111111115, carouselState1116.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111113), density2.mo406toPx0680j_4(f111111111112));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState1117 = carouselState2;
                        final float f111111111114 = f5;
                        final float f111111111115 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f111111111116 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111111117, Float f111111111118) {
                                return invoke(f111111111117.floatValue(), f111111111118.floatValue());
                            }

                            public final KeylineList invoke(float f111111111117, float f111111111118) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f111111111117, density2.mo406toPx0680j_4(f111111111114), f111111111118, carouselState1117.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111116), density2.mo406toPx0680j_4(f111111111115));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i11111110 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation18, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11111110) | (3670016 & i11111110) | (i11111110 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults1116 = CarouselDefaults.INSTANCE;
                            int i11111111 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1116.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111111, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults1117 = CarouselDefaults.INSTANCE;
                            int i11111112 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1117.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111112, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity19 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume19 = composerStartRestartGroup.consume(localDensity19);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume19;
                    Orientation orientation19 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged19 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance19 = z | zChanged19 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance19 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState1118 = carouselState2;
                        final float f111111111117 = f5;
                        final float f111111111118 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f111111111119 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111110, Float f1111111111111) {
                                return invoke(f1111111111110.floatValue(), f1111111111111.floatValue());
                            }

                            public final KeylineList invoke(float f1111111111110, float f1111111111111) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f1111111111110, density2.mo406toPx0680j_4(f111111111117), f1111111111111, carouselState1118.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111119), density2.mo406toPx0680j_4(f111111111118));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState1119 = carouselState2;
                        final float f1111111111110 = f5;
                        final float f1111111111111 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f1111111111112 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111113, Float f1111111111114) {
                                return invoke(f1111111111113.floatValue(), f1111111111114.floatValue());
                            }

                            public final KeylineList invoke(float f1111111111113, float f1111111111114) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f1111111111113, density2.mo406toPx0680j_4(f1111111111110), f1111111111114, carouselState1119.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111111112), density2.mo406toPx0680j_4(f1111111111111));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i11111113 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation19, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11111113) | (3670016 & i11111113) | (i11111113 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                        public final void invoke(Composer composer2, int i20) {
                            CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = f3;
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
            } else {
                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(fM2598getMaxSmallItemSizeD9Ej5fM$material3_release)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
                paddingValues2 = paddingValues;
            } else {
                paddingValues2 = paddingValues;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(paddingValues2)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
            }
            i12 = i3;
            if ((i2 & 256) != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i13 = 67108864;
                    } else {
                        i13 = 33554432;
                    }
                    i12 |= i13;
                }
                if ((i12 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults1118 = CarouselDefaults.INSTANCE;
                            int i11111114 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1118.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111114, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults1119 = CarouselDefaults.INSTANCE;
                            int i11111115 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1119.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111115, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity110 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume110 = composerStartRestartGroup.consume(localDensity110);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume110;
                    Orientation orientation110 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged110 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance110 = z | zChanged110 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance110 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState11110 = carouselState2;
                        final float f1111111111113 = f5;
                        final float f1111111111114 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f1111111111115 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111116, Float f1111111111117) {
                                return invoke(f1111111111116.floatValue(), f1111111111117.floatValue());
                            }

                            public final KeylineList invoke(float f1111111111116, float f1111111111117) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f1111111111116, density2.mo406toPx0680j_4(f1111111111113), f1111111111117, carouselState11110.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111111115), density2.mo406toPx0680j_4(f1111111111114));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState11111 = carouselState2;
                        final float f1111111111116 = f5;
                        final float f1111111111117 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f1111111111118 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111119, Float f11111111111110) {
                                return invoke(f1111111111119.floatValue(), f11111111111110.floatValue());
                            }

                            public final KeylineList invoke(float f1111111111119, float f11111111111110) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f1111111111119, density2.mo406toPx0680j_4(f1111111111116), f11111111111110, carouselState11111.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111111118), density2.mo406toPx0680j_4(f1111111111117));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i11111116 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation110, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11111116) | (3670016 & i11111116) | (i11111116 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults11110 = CarouselDefaults.INSTANCE;
                            int i11111117 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11110.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111117, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults11111 = CarouselDefaults.INSTANCE;
                            int i11111118 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11111.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111118, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity111 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111 = composerStartRestartGroup.consume(localDensity111);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume111;
                    Orientation orientation111 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged111 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance111 = z | zChanged111 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance111 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState11112 = carouselState2;
                        final float f1111111111119 = f5;
                        final float f11111111111110 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f11111111111111 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111111112, Float f11111111111113) {
                                return invoke(f11111111111112.floatValue(), f11111111111113.floatValue());
                            }

                            public final KeylineList invoke(float f11111111111112, float f11111111111113) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f11111111111112, density2.mo406toPx0680j_4(f1111111111119), f11111111111113, carouselState11112.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111111111), density2.mo406toPx0680j_4(f11111111111110));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState11113 = carouselState2;
                        final float f11111111111112 = f5;
                        final float f11111111111113 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f11111111111114 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111111115, Float f11111111111116) {
                                return invoke(f11111111111115.floatValue(), f11111111111116.floatValue());
                            }

                            public final KeylineList invoke(float f11111111111115, float f11111111111116) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f11111111111115, density2.mo406toPx0680j_4(f11111111111112), f11111111111116, carouselState11113.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111111114), density2.mo406toPx0680j_4(f11111111111113));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i11111119 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation111, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11111119) | (3670016 & i11111119) | (i11111119 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                        public final void invoke(Composer composer2, int i20) {
                            CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i12 |= 100663296;
            if ((i12 & 38347923) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults11112 = CarouselDefaults.INSTANCE;
                        int i111111110 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11112.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111111110, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                } else {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults11113 = CarouselDefaults.INSTANCE;
                        int i111111111 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11113.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111111111, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                }
                ProvidableCompositionLocal<Density> localDensity112 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume112 = composerStartRestartGroup.consume(localDensity112);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume112;
                Orientation orientation112 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged112 = composerStartRestartGroup.changed(density);
                i15 = i14;
                if ((i14 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                boolean zChangedInstance112 = z | zChanged112 | composerStartRestartGroup.changedInstance(carouselState2);
                if ((i15 & 458752) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = zChangedInstance112 | z2 | ((i15 & 3670016) == 1048576);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    final CarouselState carouselState11114 = carouselState2;
                    final float f11111111111115 = f5;
                    final float f11111111111116 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f11111111111117 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111111118, Float f11111111111119) {
                            return invoke(f11111111111118.floatValue(), f11111111111119.floatValue());
                        }

                        public final KeylineList invoke(float f11111111111118, float f11111111111119) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f11111111111118, density2.mo406toPx0680j_4(f11111111111115), f11111111111119, carouselState11114.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111111117), density2.mo406toPx0680j_4(f11111111111116));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    final CarouselState carouselState11115 = carouselState2;
                    final float f11111111111118 = f5;
                    final float f11111111111119 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f111111111111110 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111111111111, Float f111111111111112) {
                            return invoke(f111111111111111.floatValue(), f111111111111112.floatValue());
                        }

                        public final KeylineList invoke(float f111111111111111, float f111111111111112) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f111111111111111, density2.mo406toPx0680j_4(f11111111111118), f111111111111112, carouselState11115.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111111110), density2.mo406toPx0680j_4(f11111111111119));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i111111112 = i15 << 9;
                m2601CarouselV95POc(carouselState, orientation112, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i111111112) | (3670016 & i111111112) | (i111111112 & 29360128) | (i15 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier2 = modifier4;
                f8 = f7;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults11114 = CarouselDefaults.INSTANCE;
                        int i111111113 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11114.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111111113, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                } else {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults11115 = CarouselDefaults.INSTANCE;
                        int i111111114 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11115.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111111114, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                }
                ProvidableCompositionLocal<Density> localDensity113 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume113 = composerStartRestartGroup.consume(localDensity113);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume113;
                Orientation orientation113 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged113 = composerStartRestartGroup.changed(density);
                i15 = i14;
                if ((i14 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                boolean zChangedInstance113 = z | zChanged113 | composerStartRestartGroup.changedInstance(carouselState2);
                if ((i15 & 458752) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = zChangedInstance113 | z2 | ((i15 & 3670016) == 1048576);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    final CarouselState carouselState11116 = carouselState2;
                    final float f111111111111111 = f5;
                    final float f111111111111112 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f111111111111113 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111111111114, Float f111111111111115) {
                            return invoke(f111111111111114.floatValue(), f111111111111115.floatValue());
                        }

                        public final KeylineList invoke(float f111111111111114, float f111111111111115) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f111111111111114, density2.mo406toPx0680j_4(f111111111111111), f111111111111115, carouselState11116.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111111113), density2.mo406toPx0680j_4(f111111111111112));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    final CarouselState carouselState11117 = carouselState2;
                    final float f111111111111114 = f5;
                    final float f111111111111115 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f111111111111116 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111111111117, Float f111111111111118) {
                            return invoke(f111111111111117.floatValue(), f111111111111118.floatValue());
                        }

                        public final KeylineList invoke(float f111111111111117, float f111111111111118) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f111111111111117, density2.mo406toPx0680j_4(f111111111111114), f111111111111118, carouselState11117.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111111116), density2.mo406toPx0680j_4(f111111111111115));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i111111115 = i15 << 9;
                m2601CarouselV95POc(carouselState, orientation113, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i111111115) | (3670016 & i111111115) | (i111111115 & 29360128) | (i15 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier2 = modifier4;
                f8 = f7;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            }
            f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
            f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                    public final void invoke(Composer composer2, int i20) {
                        CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        f6 = f2;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
                if (composerStartRestartGroup.changed(targetedFlingBehaviorSingleAdvanceFlingBehavior)) {
                }
                i3 |= i17;
            } else {
                targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
            }
            i3 |= i17;
        } else {
            targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                fM2599getMinSmallItemSizeD9Ej5fM$material3_release = f3;
                if (composerStartRestartGroup.changed(fM2599getMinSmallItemSizeD9Ej5fM$material3_release)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
            } else {
                fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(fM2598getMaxSmallItemSizeD9Ej5fM$material3_release)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
                paddingValues2 = paddingValues;
            } else {
                paddingValues2 = paddingValues;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(paddingValues2)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
            }
            i12 = i3;
            if ((i2 & 256) != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i13 = 67108864;
                    } else {
                        i13 = 33554432;
                    }
                    i12 |= i13;
                }
                if ((i12 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults11116 = CarouselDefaults.INSTANCE;
                            int i111111116 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11116.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111111116, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults11117 = CarouselDefaults.INSTANCE;
                            int i111111117 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11117.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111111117, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity114 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume114 = composerStartRestartGroup.consume(localDensity114);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume114;
                    Orientation orientation114 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged114 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance114 = z | zChanged114 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance114 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState11118 = carouselState2;
                        final float f111111111111117 = f5;
                        final float f111111111111118 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f111111111111119 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111111110, Float f1111111111111111) {
                                return invoke(f1111111111111110.floatValue(), f1111111111111111.floatValue());
                            }

                            public final KeylineList invoke(float f1111111111111110, float f1111111111111111) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f1111111111111110, density2.mo406toPx0680j_4(f111111111111117), f1111111111111111, carouselState11118.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111111119), density2.mo406toPx0680j_4(f111111111111118));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState11119 = carouselState2;
                        final float f1111111111111110 = f5;
                        final float f1111111111111111 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f1111111111111112 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111111113, Float f1111111111111114) {
                                return invoke(f1111111111111113.floatValue(), f1111111111111114.floatValue());
                            }

                            public final KeylineList invoke(float f1111111111111113, float f1111111111111114) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f1111111111111113, density2.mo406toPx0680j_4(f1111111111111110), f1111111111111114, carouselState11119.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111111111112), density2.mo406toPx0680j_4(f1111111111111111));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i111111118 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation114, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i111111118) | (3670016 & i111111118) | (i111111118 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults11118 = CarouselDefaults.INSTANCE;
                            int i111111119 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11118.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i111111119, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    } else {
                        if (i16 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f6;
                        }
                        if ((i2 & 16) != 0) {
                            CarouselDefaults carouselDefaults11119 = CarouselDefaults.INSTANCE;
                            int i1111111110 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i12 &= -57345;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11119.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111111110, 2);
                        } else {
                            carouselState2 = carouselState;
                        }
                        if (i6 != 0) {
                            fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i8 != 0) {
                            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                        }
                        if (i10 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        } else {
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                        targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                        i14 = i12;
                        modifier4 = modifier3;
                        f7 = fM6890constructorimpl;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                    }
                    ProvidableCompositionLocal<Density> localDensity115 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume115 = composerStartRestartGroup.consume(localDensity115);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume115;
                    Orientation orientation115 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged115 = composerStartRestartGroup.changed(density);
                    i15 = i14;
                    if ((i14 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance115 = z | zChanged115 | composerStartRestartGroup.changedInstance(carouselState2);
                    if ((i15 & 458752) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = zChangedInstance115 | z2 | ((i15 & 3670016) == 1048576);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        final CarouselState carouselState111110 = carouselState2;
                        final float f1111111111111113 = f5;
                        final float f1111111111111114 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f1111111111111115 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111111116, Float f1111111111111117) {
                                return invoke(f1111111111111116.floatValue(), f1111111111111117.floatValue());
                            }

                            public final KeylineList invoke(float f1111111111111116, float f1111111111111117) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f1111111111111116, density2.mo406toPx0680j_4(f1111111111111113), f1111111111111117, carouselState111110.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111111111115), density2.mo406toPx0680j_4(f1111111111111114));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        final CarouselState carouselState111111 = carouselState2;
                        final float f1111111111111116 = f5;
                        final float f1111111111111117 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                        final float f1111111111111118 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111111119, Float f11111111111111110) {
                                return invoke(f1111111111111119.floatValue(), f11111111111111110.floatValue());
                            }

                            public final KeylineList invoke(float f1111111111111119, float f11111111111111110) {
                                Density density2 = density;
                                return KeylinesKt.multiBrowseKeylineList(density2, f1111111111111119, density2.mo406toPx0680j_4(f1111111111111116), f11111111111111110, carouselState111111.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111111111118), density2.mo406toPx0680j_4(f1111111111111117));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i1111111111 = i15 << 9;
                    m2601CarouselV95POc(carouselState, orientation115, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i1111111111) | (3670016 & i1111111111) | (i1111111111 & 29360128) | (i15 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier2 = modifier4;
                    f8 = f7;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                        public final void invoke(Composer composer2, int i20) {
                            CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i12 |= 100663296;
            if ((i12 & 38347923) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults111110 = CarouselDefaults.INSTANCE;
                        int i1111111112 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111110.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111111112, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                } else {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults111111 = CarouselDefaults.INSTANCE;
                        int i1111111113 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111111.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111111113, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                }
                ProvidableCompositionLocal<Density> localDensity116 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume116 = composerStartRestartGroup.consume(localDensity116);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume116;
                Orientation orientation116 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged116 = composerStartRestartGroup.changed(density);
                i15 = i14;
                if ((i14 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                boolean zChangedInstance116 = z | zChanged116 | composerStartRestartGroup.changedInstance(carouselState2);
                if ((i15 & 458752) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = zChangedInstance116 | z2 | ((i15 & 3670016) == 1048576);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    final CarouselState carouselState111112 = carouselState2;
                    final float f1111111111111119 = f5;
                    final float f11111111111111110 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f11111111111111111 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111111111112, Float f11111111111111113) {
                            return invoke(f11111111111111112.floatValue(), f11111111111111113.floatValue());
                        }

                        public final KeylineList invoke(float f11111111111111112, float f11111111111111113) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f11111111111111112, density2.mo406toPx0680j_4(f1111111111111119), f11111111111111113, carouselState111112.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111111111111), density2.mo406toPx0680j_4(f11111111111111110));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    final CarouselState carouselState111113 = carouselState2;
                    final float f11111111111111112 = f5;
                    final float f11111111111111113 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f11111111111111114 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111111111115, Float f11111111111111116) {
                            return invoke(f11111111111111115.floatValue(), f11111111111111116.floatValue());
                        }

                        public final KeylineList invoke(float f11111111111111115, float f11111111111111116) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f11111111111111115, density2.mo406toPx0680j_4(f11111111111111112), f11111111111111116, carouselState111113.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111111111114), density2.mo406toPx0680j_4(f11111111111111113));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i1111111114 = i15 << 9;
                m2601CarouselV95POc(carouselState, orientation116, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i1111111114) | (3670016 & i1111111114) | (i1111111114 & 29360128) | (i15 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier2 = modifier4;
                f8 = f7;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults111112 = CarouselDefaults.INSTANCE;
                        int i1111111115 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111112.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111111115, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                } else {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults111113 = CarouselDefaults.INSTANCE;
                        int i1111111116 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111113.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111111116, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                }
                ProvidableCompositionLocal<Density> localDensity117 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume117 = composerStartRestartGroup.consume(localDensity117);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume117;
                Orientation orientation117 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged117 = composerStartRestartGroup.changed(density);
                i15 = i14;
                if ((i14 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                boolean zChangedInstance117 = z | zChanged117 | composerStartRestartGroup.changedInstance(carouselState2);
                if ((i15 & 458752) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = zChangedInstance117 | z2 | ((i15 & 3670016) == 1048576);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    final CarouselState carouselState111114 = carouselState2;
                    final float f11111111111111115 = f5;
                    final float f11111111111111116 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f11111111111111117 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111111111118, Float f11111111111111119) {
                            return invoke(f11111111111111118.floatValue(), f11111111111111119.floatValue());
                        }

                        public final KeylineList invoke(float f11111111111111118, float f11111111111111119) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f11111111111111118, density2.mo406toPx0680j_4(f11111111111111115), f11111111111111119, carouselState111114.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111111111117), density2.mo406toPx0680j_4(f11111111111111116));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    final CarouselState carouselState111115 = carouselState2;
                    final float f11111111111111118 = f5;
                    final float f11111111111111119 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f111111111111111110 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111111111111111, Float f111111111111111112) {
                            return invoke(f111111111111111111.floatValue(), f111111111111111112.floatValue());
                        }

                        public final KeylineList invoke(float f111111111111111111, float f111111111111111112) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f111111111111111111, density2.mo406toPx0680j_4(f11111111111111118), f111111111111111112, carouselState111115.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111111111110), density2.mo406toPx0680j_4(f11111111111111119));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i1111111117 = i15 << 9;
                m2601CarouselV95POc(carouselState, orientation117, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i1111111117) | (3670016 & i1111111117) | (i1111111117 & 29360128) | (i15 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier2 = modifier4;
                f8 = f7;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            }
            f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
            f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                    public final void invoke(Composer composer2, int i20) {
                        CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = f3;
        i8 = i2 & 64;
        if (i8 != 0) {
            i3 |= 1572864;
            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
        } else {
            fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = f4;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(fM2598getMaxSmallItemSizeD9Ej5fM$material3_release)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
        }
        i10 = i2 & 128;
        if (i10 != 0) {
            i3 |= 12582912;
            paddingValues2 = paddingValues;
        } else {
            paddingValues2 = paddingValues;
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(paddingValues2)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
        }
        i12 = i3;
        if ((i2 & 256) != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i13 = 67108864;
                } else {
                    i13 = 33554432;
                }
                i12 |= i13;
            }
            if ((i12 & 38347923) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults111114 = CarouselDefaults.INSTANCE;
                        int i1111111118 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111114.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111111118, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                } else {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults111115 = CarouselDefaults.INSTANCE;
                        int i1111111119 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111115.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111111119, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                }
                ProvidableCompositionLocal<Density> localDensity118 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume118 = composerStartRestartGroup.consume(localDensity118);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume118;
                Orientation orientation118 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged118 = composerStartRestartGroup.changed(density);
                i15 = i14;
                if ((i14 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                boolean zChangedInstance118 = z | zChanged118 | composerStartRestartGroup.changedInstance(carouselState2);
                if ((i15 & 458752) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = zChangedInstance118 | z2 | ((i15 & 3670016) == 1048576);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    final CarouselState carouselState111116 = carouselState2;
                    final float f111111111111111111 = f5;
                    final float f111111111111111112 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f111111111111111113 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111111111111114, Float f111111111111111115) {
                            return invoke(f111111111111111114.floatValue(), f111111111111111115.floatValue());
                        }

                        public final KeylineList invoke(float f111111111111111114, float f111111111111111115) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f111111111111111114, density2.mo406toPx0680j_4(f111111111111111111), f111111111111111115, carouselState111116.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111111111113), density2.mo406toPx0680j_4(f111111111111111112));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    final CarouselState carouselState111117 = carouselState2;
                    final float f111111111111111114 = f5;
                    final float f111111111111111115 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f111111111111111116 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f111111111111111117, Float f111111111111111118) {
                            return invoke(f111111111111111117.floatValue(), f111111111111111118.floatValue());
                        }

                        public final KeylineList invoke(float f111111111111111117, float f111111111111111118) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f111111111111111117, density2.mo406toPx0680j_4(f111111111111111114), f111111111111111118, carouselState111117.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111111111116), density2.mo406toPx0680j_4(f111111111111111115));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i11111111110 = i15 << 9;
                m2601CarouselV95POc(carouselState, orientation118, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11111111110) | (3670016 & i11111111110) | (i11111111110 & 29360128) | (i15 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier2 = modifier4;
                f8 = f7;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults111116 = CarouselDefaults.INSTANCE;
                        int i11111111111 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111116.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111111111, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                } else {
                    if (i16 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f6;
                    }
                    if ((i2 & 16) != 0) {
                        CarouselDefaults carouselDefaults111117 = CarouselDefaults.INSTANCE;
                        int i11111111112 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i12 &= -57345;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111117.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111111112, 2);
                    } else {
                        carouselState2 = carouselState;
                    }
                    if (i6 != 0) {
                        fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i8 != 0) {
                        fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                    }
                    if (i10 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    } else {
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                    targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                    i14 = i12;
                    modifier4 = modifier3;
                    f7 = fM6890constructorimpl;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
                }
                ProvidableCompositionLocal<Density> localDensity119 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume119 = composerStartRestartGroup.consume(localDensity119);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume119;
                Orientation orientation119 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged119 = composerStartRestartGroup.changed(density);
                i15 = i14;
                if ((i14 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                boolean zChangedInstance119 = z | zChanged119 | composerStartRestartGroup.changedInstance(carouselState2);
                if ((i15 & 458752) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = zChangedInstance119 | z2 | ((i15 & 3670016) == 1048576);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    final CarouselState carouselState111118 = carouselState2;
                    final float f111111111111111117 = f5;
                    final float f111111111111111118 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f111111111111111119 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111111111110, Float f1111111111111111111) {
                            return invoke(f1111111111111111110.floatValue(), f1111111111111111111.floatValue());
                        }

                        public final KeylineList invoke(float f1111111111111111110, float f1111111111111111111) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f1111111111111111110, density2.mo406toPx0680j_4(f111111111111111117), f1111111111111111111, carouselState111118.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f111111111111111119), density2.mo406toPx0680j_4(f111111111111111118));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    final CarouselState carouselState111119 = carouselState2;
                    final float f1111111111111111110 = f5;
                    final float f1111111111111111111 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                    final float f1111111111111111112 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111111111113, Float f1111111111111111114) {
                            return invoke(f1111111111111111113.floatValue(), f1111111111111111114.floatValue());
                        }

                        public final KeylineList invoke(float f1111111111111111113, float f1111111111111111114) {
                            Density density2 = density;
                            return KeylinesKt.multiBrowseKeylineList(density2, f1111111111111111113, density2.mo406toPx0680j_4(f1111111111111111110), f1111111111111111114, carouselState111119.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111111111111112), density2.mo406toPx0680j_4(f1111111111111111111));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i11111111113 = i15 << 9;
                m2601CarouselV95POc(carouselState, orientation119, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11111111113) | (3670016 & i11111111113) | (i11111111113 & 29360128) | (i15 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier2 = modifier4;
                f8 = f7;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            }
            f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
            f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                    public final void invoke(Composer composer2, int i20) {
                        CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i12 |= 100663296;
        if ((i12 & 38347923) == 38347922) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i16 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f6;
                }
                if ((i2 & 16) != 0) {
                    CarouselDefaults carouselDefaults111118 = CarouselDefaults.INSTANCE;
                    int i11111111114 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                    carouselState2 = carouselState;
                    i12 &= -57345;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111118.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111111114, 2);
                } else {
                    carouselState2 = carouselState;
                }
                if (i6 != 0) {
                    fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i8 != 0) {
                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i10 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                } else {
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
                targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                i14 = i12;
                modifier4 = modifier3;
                f7 = fM6890constructorimpl;
            } else {
                if (i16 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f6;
                }
                if ((i2 & 16) != 0) {
                    CarouselDefaults carouselDefaults111119 = CarouselDefaults.INSTANCE;
                    int i11111111115 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                    carouselState2 = carouselState;
                    i12 &= -57345;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults111119.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111111115, 2);
                } else {
                    carouselState2 = carouselState;
                }
                if (i6 != 0) {
                    fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i8 != 0) {
                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i10 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                } else {
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
                targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                i14 = i12;
                modifier4 = modifier3;
                f7 = fM6890constructorimpl;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
            }
            ProvidableCompositionLocal<Density> localDensity1110 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume1110 = composerStartRestartGroup.consume(localDensity1110);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            density = (Density) objConsume1110;
            Orientation orientation1110 = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
            boolean zChanged1110 = composerStartRestartGroup.changed(density);
            i15 = i14;
            if ((i14 & 112) == 32) {
                z = true;
            } else {
                z = false;
            }
            boolean zChangedInstance1110 = z | zChanged1110 | composerStartRestartGroup.changedInstance(carouselState2);
            if ((i15 & 458752) == 131072) {
                z2 = true;
            } else {
                z2 = false;
            }
            z3 = zChangedInstance1110 | z2 | ((i15 & 3670016) == 1048576);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z3) {
                final CarouselState carouselState1111110 = carouselState2;
                final float f1111111111111111113 = f5;
                final float f1111111111111111114 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                final float f1111111111111111115 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111111111116, Float f1111111111111111117) {
                        return invoke(f1111111111111111116.floatValue(), f1111111111111111117.floatValue());
                    }

                    public final KeylineList invoke(float f1111111111111111116, float f1111111111111111117) {
                        Density density2 = density;
                        return KeylinesKt.multiBrowseKeylineList(density2, f1111111111111111116, density2.mo406toPx0680j_4(f1111111111111111113), f1111111111111111117, carouselState1111110.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111111111111115), density2.mo406toPx0680j_4(f1111111111111111114));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                final CarouselState carouselState1111111 = carouselState2;
                final float f1111111111111111116 = f5;
                final float f1111111111111111117 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                final float f1111111111111111118 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111111111111111119, Float f11111111111111111110) {
                        return invoke(f1111111111111111119.floatValue(), f11111111111111111110.floatValue());
                    }

                    public final KeylineList invoke(float f1111111111111111119, float f11111111111111111110) {
                        Density density2 = density;
                        return KeylinesKt.multiBrowseKeylineList(density2, f1111111111111111119, density2.mo406toPx0680j_4(f1111111111111111116), f11111111111111111110, carouselState1111111.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f1111111111111111118), density2.mo406toPx0680j_4(f1111111111111111117));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i11111111116 = i15 << 9;
            m2601CarouselV95POc(carouselState, orientation1110, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11111111116) | (3670016 & i11111111116) | (i11111111116 & 29360128) | (i15 & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
            modifier2 = modifier4;
            f8 = f7;
            targetedFlingBehavior3 = targetedFlingBehavior2;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i16 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f6;
                }
                if ((i2 & 16) != 0) {
                    CarouselDefaults carouselDefaults1111110 = CarouselDefaults.INSTANCE;
                    int i11111111117 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                    carouselState2 = carouselState;
                    i12 &= -57345;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1111110.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111111117, 2);
                } else {
                    carouselState2 = carouselState;
                }
                if (i6 != 0) {
                    fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i8 != 0) {
                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i10 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                } else {
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
                targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                i14 = i12;
                modifier4 = modifier3;
                f7 = fM6890constructorimpl;
            } else {
                if (i16 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f6;
                }
                if ((i2 & 16) != 0) {
                    CarouselDefaults carouselDefaults1111111 = CarouselDefaults.INSTANCE;
                    int i11111111118 = (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                    carouselState2 = carouselState;
                    i12 &= -57345;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults1111111.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i11111111118, 2);
                } else {
                    carouselState2 = carouselState;
                }
                if (i6 != 0) {
                    fM2599getMinSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2599getMinSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i8 != 0) {
                    fM2598getMaxSmallItemSizeD9Ej5fM$material3_release = CarouselDefaults.INSTANCE.m2598getMaxSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i10 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                } else {
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
                targetedFlingBehavior2 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                i14 = i12;
                modifier4 = modifier3;
                f7 = fM6890constructorimpl;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1825706865, i14, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
            }
            ProvidableCompositionLocal<Density> localDensity1111 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume1111 = composerStartRestartGroup.consume(localDensity1111);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            density = (Density) objConsume1111;
            Orientation orientation1111 = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1995157598, "CC(remember):Carousel.kt#9igjgp");
            boolean zChanged1111 = composerStartRestartGroup.changed(density);
            i15 = i14;
            if ((i14 & 112) == 32) {
                z = true;
            } else {
                z = false;
            }
            boolean zChangedInstance1111 = z | zChanged1111 | composerStartRestartGroup.changedInstance(carouselState2);
            if ((i15 & 458752) == 131072) {
                z2 = true;
            } else {
                z2 = false;
            }
            z3 = zChangedInstance1111 | z2 | ((i15 & 3670016) == 1048576);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z3) {
                final CarouselState carouselState1111112 = carouselState2;
                final float f1111111111111111119 = f5;
                final float f11111111111111111110 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                final float f11111111111111111111 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111111111111112, Float f11111111111111111113) {
                        return invoke(f11111111111111111112.floatValue(), f11111111111111111113.floatValue());
                    }

                    public final KeylineList invoke(float f11111111111111111112, float f11111111111111111113) {
                        Density density2 = density;
                        return KeylinesKt.multiBrowseKeylineList(density2, f11111111111111111112, density2.mo406toPx0680j_4(f1111111111111111119), f11111111111111111113, carouselState1111112.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111111111111111), density2.mo406toPx0680j_4(f11111111111111111110));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                final CarouselState carouselState1111113 = carouselState2;
                final float f11111111111111111112 = f5;
                final float f11111111111111111113 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
                final float f11111111111111111114 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f11111111111111111115, Float f11111111111111111116) {
                        return invoke(f11111111111111111115.floatValue(), f11111111111111111116.floatValue());
                    }

                    public final KeylineList invoke(float f11111111111111111115, float f11111111111111111116) {
                        Density density2 = density;
                        return KeylinesKt.multiBrowseKeylineList(density2, f11111111111111111115, density2.mo406toPx0680j_4(f11111111111111111112), f11111111111111111116, carouselState1111113.getItemCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(f11111111111111111114), density2.mo406toPx0680j_4(f11111111111111111113));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i11111111119 = i15 << 9;
            m2601CarouselV95POc(carouselState, orientation1111, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 2, modifier4, f7, targetedFlingBehavior2, function4, composerStartRestartGroup, (i15 & 14) | 24624 | ((i15 >> 12) & 7168) | (458752 & i11111111119) | (3670016 & i11111111119) | (i11111111119 & 29360128) | (i15 & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
            modifier2 = modifier4;
            f8 = f7;
            targetedFlingBehavior3 = targetedFlingBehavior2;
        }
        f9 = fM2598getMaxSmallItemSizeD9Ej5fM$material3_release;
        f10 = fM2599getMinSmallItemSizeD9Ej5fM$material3_release;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                public final void invoke(Composer composer2, int i20) {
                    CarouselKt.m2602HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier2, f8, targetedFlingBehavior3, f10, f9, paddingValues3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x011f  */
    /* JADX WARN: Code duplicated, block: B:103:0x012a  */
    /* JADX WARN: Code duplicated, block: B:106:0x013f  */
    /* JADX WARN: Code duplicated, block: B:109:0x0170  */
    /* JADX WARN: Code duplicated, block: B:110:0x0172  */
    /* JADX WARN: Code duplicated, block: B:113:0x017a  */
    /* JADX WARN: Code duplicated, block: B:115:0x0182  */
    /* JADX WARN: Code duplicated, block: B:118:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:122:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:124:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0064  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:39:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0073  */
    /* JADX WARN: Code duplicated, block: B:42:0x0076  */
    /* JADX WARN: Code duplicated, block: B:47:0x0080  */
    /* JADX WARN: Code duplicated, block: B:49:0x0084  */
    /* JADX WARN: Code duplicated, block: B:51:0x008c  */
    /* JADX WARN: Code duplicated, block: B:52:0x008f  */
    /* JADX WARN: Code duplicated, block: B:55:0x0095  */
    /* JADX WARN: Code duplicated, block: B:58:0x009d  */
    /* JADX WARN: Code duplicated, block: B:59:0x009f  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:63:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:84:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:92:0x0106 A[PHI: r3 r6 r10 r11
      0x0106: PHI (r3v27 int) = (r3v21 int), (r3v20 int), (r3v28 int) binds: [B:102:0x0128, B:90:0x0102, B:91:0x0104] A[DONT_GENERATE, DONT_INLINE]
      0x0106: PHI (r6v7 androidx.compose.ui.Modifier) = (r6v3 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier) binds: [B:102:0x0128, B:90:0x0102, B:91:0x0104] A[DONT_GENERATE, DONT_INLINE]
      0x0106: PHI (r10v7 float) = (r10v3 float), (r10v2 float), (r10v2 float) binds: [B:102:0x0128, B:90:0x0102, B:91:0x0104] A[DONT_GENERATE, DONT_INLINE]
      0x0106: PHI (r11v12 androidx.compose.foundation.gestures.TargetedFlingBehavior) = 
      (r11v7 androidx.compose.foundation.gestures.TargetedFlingBehavior)
      (r11v6 androidx.compose.foundation.gestures.TargetedFlingBehavior)
      (r11v6 androidx.compose.foundation.gestures.TargetedFlingBehavior)
     binds: [B:102:0x0128, B:90:0x0102, B:91:0x0104] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:94:0x010c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x010e  */
    /* JADX WARN: Code duplicated, block: B:98:0x0116  */
    /* JADX INFO: renamed from: HorizontalUncontainedCarousel-9QcgTRs, reason: not valid java name */
    public static final void m2603HorizontalUncontainedCarousel9QcgTRs(final CarouselState carouselState, final float f, Modifier modifier, float f2, TargetedFlingBehavior targetedFlingBehavior, PaddingValues paddingValues, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i, final int i2) {
        CarouselState carouselState2;
        int i3;
        Modifier modifier2;
        int i4;
        float fM6890constructorimpl;
        int i5;
        TargetedFlingBehavior targetedFlingBehaviorNoSnapFlingBehavior;
        int i6;
        PaddingValues paddingValues2;
        int i7;
        Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function5;
        int i8;
        PaddingValues paddingValuesM713PaddingValues0680j_4;
        TargetedFlingBehavior targetedFlingBehavior2;
        final Density density;
        boolean z;
        boolean z2;
        Object objRememberedValue;
        Composer composer2;
        final PaddingValues paddingValues3;
        final Modifier modifier3;
        final float f3;
        final TargetedFlingBehavior targetedFlingBehavior3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(529322840);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(HorizontalUncontainedCarousel)P(6,4:c#ui.unit.Dp,5,3:c#ui.unit.Dp,2,1)175@8483L21,179@8666L7,183@8779L337,180@8678L818:Carousel.kt#dcf9yb");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            carouselState2 = carouselState;
        } else {
            carouselState2 = carouselState;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changedInstance(carouselState2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        int i9 = i2 & 4;
        if (i9 == 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    fM6890constructorimpl = f2;
                    if (composerStartRestartGroup.changed(fM6890constructorimpl)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
                        int i10 = composerStartRestartGroup.changed(targetedFlingBehaviorNoSnapFlingBehavior) ? 16384 : 8192;
                        i3 |= i10;
                    } else {
                        targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
                    }
                    i3 |= i10;
                } else {
                    targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        paddingValues2 = paddingValues;
                        if (composerStartRestartGroup.changed(paddingValues2)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((i2 & 64) != 0) {
                        if ((i & 1572864) == 0) {
                            function5 = function4;
                            if (composerStartRestartGroup.changedInstance(function5)) {
                                i8 = 1048576;
                            } else {
                                i8 = 524288;
                            }
                            i3 |= i8;
                        }
                        if ((599187 & i3) == 599186 || !composerStartRestartGroup.getSkipping()) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                            } else {
                                if (i9 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                    targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                                }
                                if (i6 != 0) {
                                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                    targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                }
                                float f4 = fM6890constructorimpl;
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                                }
                                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                Object objConsume = composerStartRestartGroup.consume(localDensity);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                density = (Density) objConsume;
                                Orientation orientation = Orientation.Horizontal;
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                                boolean zChanged = composerStartRestartGroup.changed(density);
                                if ((i3 & 112) == 32) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                z2 = zChanged | z;
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (!z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f5, Float f6) {
                                            return invoke(f5.floatValue(), f6.floatValue());
                                        }

                                        public final KeylineList invoke(float f5, float f6) {
                                            Density density2 = density;
                                            return KeylinesKt.uncontainedKeylineList(density2, f5, density2.mo406toPx0680j_4(f), f6);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                int i11 = i3 << 9;
                                composer2 = composerStartRestartGroup;
                                Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function6 = function5;
                                Modifier modifier4 = modifier2;
                                m2601CarouselV95POc(carouselState2, orientation, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier4, f4, targetedFlingBehavior2, function6, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i11) | (3670016 & i11) | (i11 & 29360128) | ((i3 << 6) & 234881024), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                                modifier3 = modifier4;
                                f3 = f4;
                                targetedFlingBehavior3 = targetedFlingBehavior2;
                            }
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            float f5 = fM6890constructorimpl;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                            }
                            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = composerStartRestartGroup.consume(localDensity2);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            density = (Density) objConsume2;
                            Orientation orientation2 = Orientation.Horizontal;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                            boolean zChanged2 = composerStartRestartGroup.changed(density);
                            if ((i3 & 112) == 32) {
                                z = true;
                            } else {
                                z = false;
                            }
                            z2 = zChanged2 | z;
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!z2) {
                                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f6, Float f7) {
                                        return invoke(f6.floatValue(), f7.floatValue());
                                    }

                                    public final KeylineList invoke(float f6, float f7) {
                                        Density density2 = density;
                                        return KeylinesKt.uncontainedKeylineList(density2, f6, density2.mo406toPx0680j_4(f), f7);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f6, Float f7) {
                                        return invoke(f6.floatValue(), f7.floatValue());
                                    }

                                    public final KeylineList invoke(float f6, float f7) {
                                        Density density2 = density;
                                        return KeylinesKt.uncontainedKeylineList(density2, f6, density2.mo406toPx0680j_4(f), f7);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            int i12 = i3 << 9;
                            composer2 = composerStartRestartGroup;
                            Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function7 = function5;
                            Modifier modifier5 = modifier2;
                            m2601CarouselV95POc(carouselState2, orientation2, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier5, f5, targetedFlingBehavior2, function7, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i12) | (3670016 & i12) | (i12 & 29360128) | ((i3 << 6) & 234881024), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                            modifier3 = modifier5;
                            f3 = f5;
                            targetedFlingBehavior3 = targetedFlingBehavior2;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            composer2 = composerStartRestartGroup;
                            modifier3 = modifier2;
                            f3 = fM6890constructorimpl;
                            targetedFlingBehavior3 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValues3 = paddingValues2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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

                                public final void invoke(Composer composer3, int i13) {
                                    CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    function5 = function4;
                    if ((599187 & i3) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        }
                        float f6 = fM6890constructorimpl;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                        }
                        ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = composerStartRestartGroup.consume(localDensity3);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume3;
                        Orientation orientation3 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged3 = composerStartRestartGroup.changed(density);
                        if ((i3 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = zChanged3 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f7, Float f8) {
                                    return invoke(f7.floatValue(), f8.floatValue());
                                }

                                public final KeylineList invoke(float f7, float f8) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f7, density2.mo406toPx0680j_4(f), f8);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f7, Float f8) {
                                    return invoke(f7.floatValue(), f8.floatValue());
                                }

                                public final KeylineList invoke(float f7, float f8) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f7, density2.mo406toPx0680j_4(f), f8);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i13 = i3 << 9;
                        composer2 = composerStartRestartGroup;
                        Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function8 = function5;
                        Modifier modifier6 = modifier2;
                        m2601CarouselV95POc(carouselState2, orientation3, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier6, f6, targetedFlingBehavior2, function8, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i13) | (3670016 & i13) | (i13 & 29360128) | ((i3 << 6) & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier3 = modifier6;
                        f3 = f6;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        }
                        float f7 = fM6890constructorimpl;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                        }
                        ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = composerStartRestartGroup.consume(localDensity4);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume4;
                        Orientation orientation4 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged4 = composerStartRestartGroup.changed(density);
                        if ((i3 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = zChanged4 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f8, Float f9) {
                                    return invoke(f8.floatValue(), f9.floatValue());
                                }

                                public final KeylineList invoke(float f8, float f9) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f8, density2.mo406toPx0680j_4(f), f9);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f8, Float f9) {
                                    return invoke(f8.floatValue(), f9.floatValue());
                                }

                                public final KeylineList invoke(float f8, float f9) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f8, density2.mo406toPx0680j_4(f), f9);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i14 = i3 << 9;
                        composer2 = composerStartRestartGroup;
                        Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function9 = function5;
                        Modifier modifier7 = modifier2;
                        m2601CarouselV95POc(carouselState2, orientation4, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier7, f7, targetedFlingBehavior2, function9, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i14) | (3670016 & i14) | (i14 & 29360128) | ((i3 << 6) & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier3 = modifier7;
                        f3 = f7;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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

                            public final void invoke(Composer composer3, int i15) {
                                CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                paddingValues2 = paddingValues;
                if ((i2 & 64) != 0) {
                    if ((i & 1572864) == 0) {
                        function5 = function4;
                        if (composerStartRestartGroup.changedInstance(function5)) {
                            i8 = 1048576;
                        } else {
                            i8 = 524288;
                        }
                        i3 |= i8;
                    }
                    if ((599187 & i3) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        }
                        float f8 = fM6890constructorimpl;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                        }
                        ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume5 = composerStartRestartGroup.consume(localDensity5);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume5;
                        Orientation orientation5 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged5 = composerStartRestartGroup.changed(density);
                        if ((i3 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = zChanged5 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f9, Float f10) {
                                    return invoke(f9.floatValue(), f10.floatValue());
                                }

                                public final KeylineList invoke(float f9, float f10) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f9, density2.mo406toPx0680j_4(f), f10);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f9, Float f10) {
                                    return invoke(f9.floatValue(), f10.floatValue());
                                }

                                public final KeylineList invoke(float f9, float f10) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f9, density2.mo406toPx0680j_4(f), f10);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i15 = i3 << 9;
                        composer2 = composerStartRestartGroup;
                        Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function10 = function5;
                        Modifier modifier8 = modifier2;
                        m2601CarouselV95POc(carouselState2, orientation5, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier8, f8, targetedFlingBehavior2, function10, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i15) | (3670016 & i15) | (i15 & 29360128) | ((i3 << 6) & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier3 = modifier8;
                        f3 = f8;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        }
                        float f9 = fM6890constructorimpl;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                        }
                        ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume6 = composerStartRestartGroup.consume(localDensity6);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume6;
                        Orientation orientation6 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged6 = composerStartRestartGroup.changed(density);
                        if ((i3 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = zChanged6 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f10, Float f11) {
                                    return invoke(f10.floatValue(), f11.floatValue());
                                }

                                public final KeylineList invoke(float f10, float f11) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f10, density2.mo406toPx0680j_4(f), f11);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f10, Float f11) {
                                    return invoke(f10.floatValue(), f11.floatValue());
                                }

                                public final KeylineList invoke(float f10, float f11) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f10, density2.mo406toPx0680j_4(f), f11);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i16 = i3 << 9;
                        composer2 = composerStartRestartGroup;
                        Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function11 = function5;
                        Modifier modifier9 = modifier2;
                        m2601CarouselV95POc(carouselState2, orientation6, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier9, f9, targetedFlingBehavior2, function11, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i16) | (3670016 & i16) | (i16 & 29360128) | ((i3 << 6) & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier3 = modifier9;
                        f3 = f9;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                                CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                function5 = function4;
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f10 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume7 = composerStartRestartGroup.consume(localDensity7);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume7;
                    Orientation orientation7 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged7 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged7 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f11, Float f12) {
                                return invoke(f11.floatValue(), f12.floatValue());
                            }

                            public final KeylineList invoke(float f11, float f12) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f11, density2.mo406toPx0680j_4(f), f12);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f11, Float f12) {
                                return invoke(f11.floatValue(), f12.floatValue());
                            }

                            public final KeylineList invoke(float f11, float f12) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f11, density2.mo406toPx0680j_4(f), f12);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i17 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function12 = function5;
                    Modifier modifier10 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation7, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier10, f10, targetedFlingBehavior2, function12, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i17) | (3670016 & i17) | (i17 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier10;
                    f3 = f10;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f11 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume8 = composerStartRestartGroup.consume(localDensity8);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume8;
                    Orientation orientation8 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged8 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged8 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f12, Float f13) {
                                return invoke(f12.floatValue(), f13.floatValue());
                            }

                            public final KeylineList invoke(float f12, float f13) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f12, density2.mo406toPx0680j_4(f), f13);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f12, Float f13) {
                                return invoke(f12.floatValue(), f13.floatValue());
                            }

                            public final KeylineList invoke(float f12, float f13) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f12, density2.mo406toPx0680j_4(f), f13);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i18 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function13 = function5;
                    Modifier modifier11 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation8, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier11, f11, targetedFlingBehavior2, function13, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i18) | (3670016 & i18) | (i18 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier11;
                    f3 = f11;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                            CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            fM6890constructorimpl = f2;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
                    if (composerStartRestartGroup.changed(targetedFlingBehaviorNoSnapFlingBehavior)) {
                    }
                    i3 |= i10;
                } else {
                    targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
                }
                i3 |= i10;
            } else {
                targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    paddingValues2 = paddingValues;
                    if (composerStartRestartGroup.changed(paddingValues2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((i2 & 64) != 0) {
                    if ((i & 1572864) == 0) {
                        function5 = function4;
                        if (composerStartRestartGroup.changedInstance(function5)) {
                            i8 = 1048576;
                        } else {
                            i8 = 524288;
                        }
                        i3 |= i8;
                    }
                    if ((599187 & i3) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        }
                        float f12 = fM6890constructorimpl;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                        }
                        ProvidableCompositionLocal<Density> localDensity9 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume9 = composerStartRestartGroup.consume(localDensity9);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume9;
                        Orientation orientation9 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged9 = composerStartRestartGroup.changed(density);
                        if ((i3 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = zChanged9 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f13, Float f14) {
                                    return invoke(f13.floatValue(), f14.floatValue());
                                }

                                public final KeylineList invoke(float f13, float f14) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f13, density2.mo406toPx0680j_4(f), f14);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f13, Float f14) {
                                    return invoke(f13.floatValue(), f14.floatValue());
                                }

                                public final KeylineList invoke(float f13, float f14) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f13, density2.mo406toPx0680j_4(f), f14);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i19 = i3 << 9;
                        composer2 = composerStartRestartGroup;
                        Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function14 = function5;
                        Modifier modifier12 = modifier2;
                        m2601CarouselV95POc(carouselState2, orientation9, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier12, f12, targetedFlingBehavior2, function14, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i19) | (3670016 & i19) | (i19 & 29360128) | ((i3 << 6) & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier3 = modifier12;
                        f3 = f12;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        }
                        float f13 = fM6890constructorimpl;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                        }
                        ProvidableCompositionLocal<Density> localDensity10 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume10 = composerStartRestartGroup.consume(localDensity10);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume10;
                        Orientation orientation10 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged10 = composerStartRestartGroup.changed(density);
                        if ((i3 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = zChanged10 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f14, Float f15) {
                                    return invoke(f14.floatValue(), f15.floatValue());
                                }

                                public final KeylineList invoke(float f14, float f15) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f14, density2.mo406toPx0680j_4(f), f15);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f14, Float f15) {
                                    return invoke(f14.floatValue(), f15.floatValue());
                                }

                                public final KeylineList invoke(float f14, float f15) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f14, density2.mo406toPx0680j_4(f), f15);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i110 = i3 << 9;
                        composer2 = composerStartRestartGroup;
                        Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function15 = function5;
                        Modifier modifier13 = modifier2;
                        m2601CarouselV95POc(carouselState2, orientation10, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier13, f13, targetedFlingBehavior2, function15, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i110) | (3670016 & i110) | (i110 & 29360128) | ((i3 << 6) & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier3 = modifier13;
                        f3 = f13;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                                CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                function5 = function4;
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f14 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity11 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11 = composerStartRestartGroup.consume(localDensity11);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume11;
                    Orientation orientation11 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged11 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged11 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f15, Float f16) {
                                return invoke(f15.floatValue(), f16.floatValue());
                            }

                            public final KeylineList invoke(float f15, float f16) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f15, density2.mo406toPx0680j_4(f), f16);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f15, Float f16) {
                                return invoke(f15.floatValue(), f16.floatValue());
                            }

                            public final KeylineList invoke(float f15, float f16) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f15, density2.mo406toPx0680j_4(f), f16);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i111 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function16 = function5;
                    Modifier modifier14 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation11, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier14, f14, targetedFlingBehavior2, function16, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i111) | (3670016 & i111) | (i111 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier14;
                    f3 = f14;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f15 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity12 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume12 = composerStartRestartGroup.consume(localDensity12);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume12;
                    Orientation orientation12 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged12 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged12 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f16, Float f17) {
                                return invoke(f16.floatValue(), f17.floatValue());
                            }

                            public final KeylineList invoke(float f16, float f17) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f16, density2.mo406toPx0680j_4(f), f17);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f16, Float f17) {
                                return invoke(f16.floatValue(), f17.floatValue());
                            }

                            public final KeylineList invoke(float f16, float f17) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f16, density2.mo406toPx0680j_4(f), f17);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i112 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function17 = function5;
                    Modifier modifier15 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation12, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier15, f15, targetedFlingBehavior2, function17, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i112) | (3670016 & i112) | (i112 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier15;
                    f3 = f15;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                            CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            paddingValues2 = paddingValues;
            if ((i2 & 64) != 0) {
                if ((i & 1572864) == 0) {
                    function5 = function4;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i8 = 1048576;
                    } else {
                        i8 = 524288;
                    }
                    i3 |= i8;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f16 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity13 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume13 = composerStartRestartGroup.consume(localDensity13);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume13;
                    Orientation orientation13 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged13 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged13 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f17, Float f18) {
                                return invoke(f17.floatValue(), f18.floatValue());
                            }

                            public final KeylineList invoke(float f17, float f18) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f17, density2.mo406toPx0680j_4(f), f18);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f17, Float f18) {
                                return invoke(f17.floatValue(), f18.floatValue());
                            }

                            public final KeylineList invoke(float f17, float f18) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f17, density2.mo406toPx0680j_4(f), f18);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i113 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function18 = function5;
                    Modifier modifier16 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation13, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier16, f16, targetedFlingBehavior2, function18, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i113) | (3670016 & i113) | (i113 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier16;
                    f3 = f16;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f17 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity14 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume14 = composerStartRestartGroup.consume(localDensity14);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume14;
                    Orientation orientation14 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged14 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged14 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f18, Float f19) {
                                return invoke(f18.floatValue(), f19.floatValue());
                            }

                            public final KeylineList invoke(float f18, float f19) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f18, density2.mo406toPx0680j_4(f), f19);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f18, Float f19) {
                                return invoke(f18.floatValue(), f19.floatValue());
                            }

                            public final KeylineList invoke(float f18, float f19) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f18, density2.mo406toPx0680j_4(f), f19);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i114 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function19 = function5;
                    Modifier modifier17 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation14, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier17, f17, targetedFlingBehavior2, function19, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i114) | (3670016 & i114) | (i114 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier17;
                    f3 = f17;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                            CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            function5 = function4;
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                }
                float f18 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                }
                ProvidableCompositionLocal<Density> localDensity15 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume15 = composerStartRestartGroup.consume(localDensity15);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume15;
                Orientation orientation15 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged15 = composerStartRestartGroup.changed(density);
                if ((i3 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = zChanged15 | z;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f19, Float f110) {
                            return invoke(f19.floatValue(), f110.floatValue());
                        }

                        public final KeylineList invoke(float f19, float f110) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f19, density2.mo406toPx0680j_4(f), f110);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f19, Float f110) {
                            return invoke(f19.floatValue(), f110.floatValue());
                        }

                        public final KeylineList invoke(float f19, float f110) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f19, density2.mo406toPx0680j_4(f), f110);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i115 = i3 << 9;
                composer2 = composerStartRestartGroup;
                Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function110 = function5;
                Modifier modifier18 = modifier2;
                m2601CarouselV95POc(carouselState2, orientation15, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier18, f18, targetedFlingBehavior2, function110, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i115) | (3670016 & i115) | (i115 & 29360128) | ((i3 << 6) & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier3 = modifier18;
                f3 = f18;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                }
                float f19 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                }
                ProvidableCompositionLocal<Density> localDensity16 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume16 = composerStartRestartGroup.consume(localDensity16);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume16;
                Orientation orientation16 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged16 = composerStartRestartGroup.changed(density);
                if ((i3 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = zChanged16 | z;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f110, Float f111) {
                            return invoke(f110.floatValue(), f111.floatValue());
                        }

                        public final KeylineList invoke(float f110, float f111) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f110, density2.mo406toPx0680j_4(f), f111);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f110, Float f111) {
                            return invoke(f110.floatValue(), f111.floatValue());
                        }

                        public final KeylineList invoke(float f110, float f111) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f110, density2.mo406toPx0680j_4(f), f111);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i116 = i3 << 9;
                composer2 = composerStartRestartGroup;
                Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function111 = function5;
                Modifier modifier19 = modifier2;
                m2601CarouselV95POc(carouselState2, orientation16, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier19, f19, targetedFlingBehavior2, function111, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i116) | (3670016 & i116) | (i116 & 29360128) | ((i3 << 6) & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier3 = modifier19;
                f3 = f19;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                        CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                fM6890constructorimpl = f2;
                if (composerStartRestartGroup.changed(fM6890constructorimpl)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
                    if (composerStartRestartGroup.changed(targetedFlingBehaviorNoSnapFlingBehavior)) {
                    }
                    i3 |= i10;
                } else {
                    targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
                }
                i3 |= i10;
            } else {
                targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    paddingValues2 = paddingValues;
                    if (composerStartRestartGroup.changed(paddingValues2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((i2 & 64) != 0) {
                    if ((i & 1572864) == 0) {
                        function5 = function4;
                        if (composerStartRestartGroup.changedInstance(function5)) {
                            i8 = 1048576;
                        } else {
                            i8 = 524288;
                        }
                        i3 |= i8;
                    }
                    if ((599187 & i3) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        }
                        float f110 = fM6890constructorimpl;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                        }
                        ProvidableCompositionLocal<Density> localDensity17 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume17 = composerStartRestartGroup.consume(localDensity17);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume17;
                        Orientation orientation17 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged17 = composerStartRestartGroup.changed(density);
                        if ((i3 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = zChanged17 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f111, Float f112) {
                                    return invoke(f111.floatValue(), f112.floatValue());
                                }

                                public final KeylineList invoke(float f111, float f112) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f111, density2.mo406toPx0680j_4(f), f112);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f111, Float f112) {
                                    return invoke(f111.floatValue(), f112.floatValue());
                                }

                                public final KeylineList invoke(float f111, float f112) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f111, density2.mo406toPx0680j_4(f), f112);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i117 = i3 << 9;
                        composer2 = composerStartRestartGroup;
                        Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function112 = function5;
                        Modifier modifier110 = modifier2;
                        m2601CarouselV95POc(carouselState2, orientation17, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier110, f110, targetedFlingBehavior2, function112, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i117) | (3670016 & i117) | (i117 & 29360128) | ((i3 << 6) & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier3 = modifier110;
                        f3 = f110;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                                paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                            }
                        }
                        float f111 = fM6890constructorimpl;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                        }
                        ProvidableCompositionLocal<Density> localDensity18 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume18 = composerStartRestartGroup.consume(localDensity18);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        density = (Density) objConsume18;
                        Orientation orientation18 = Orientation.Horizontal;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged18 = composerStartRestartGroup.changed(density);
                        if ((i3 & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = zChanged18 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z2) {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f112, Float f113) {
                                    return invoke(f112.floatValue(), f113.floatValue());
                                }

                                public final KeylineList invoke(float f112, float f113) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f112, density2.mo406toPx0680j_4(f), f113);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ KeylineList invoke(Float f112, Float f113) {
                                    return invoke(f112.floatValue(), f113.floatValue());
                                }

                                public final KeylineList invoke(float f112, float f113) {
                                    Density density2 = density;
                                    return KeylinesKt.uncontainedKeylineList(density2, f112, density2.mo406toPx0680j_4(f), f113);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i118 = i3 << 9;
                        composer2 = composerStartRestartGroup;
                        Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function113 = function5;
                        Modifier modifier111 = modifier2;
                        m2601CarouselV95POc(carouselState2, orientation18, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier111, f111, targetedFlingBehavior2, function113, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i118) | (3670016 & i118) | (i118 & 29360128) | ((i3 << 6) & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                        modifier3 = modifier111;
                        f3 = f111;
                        targetedFlingBehavior3 = targetedFlingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                                CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                function5 = function4;
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f112 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity19 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume19 = composerStartRestartGroup.consume(localDensity19);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume19;
                    Orientation orientation19 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged19 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged19 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f113, Float f114) {
                                return invoke(f113.floatValue(), f114.floatValue());
                            }

                            public final KeylineList invoke(float f113, float f114) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f113, density2.mo406toPx0680j_4(f), f114);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f113, Float f114) {
                                return invoke(f113.floatValue(), f114.floatValue());
                            }

                            public final KeylineList invoke(float f113, float f114) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f113, density2.mo406toPx0680j_4(f), f114);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i119 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function114 = function5;
                    Modifier modifier112 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation19, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier112, f112, targetedFlingBehavior2, function114, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i119) | (3670016 & i119) | (i119 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier112;
                    f3 = f112;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f113 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity110 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume110 = composerStartRestartGroup.consume(localDensity110);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume110;
                    Orientation orientation110 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged110 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged110 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f114, Float f115) {
                                return invoke(f114.floatValue(), f115.floatValue());
                            }

                            public final KeylineList invoke(float f114, float f115) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f114, density2.mo406toPx0680j_4(f), f115);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f114, Float f115) {
                                return invoke(f114.floatValue(), f115.floatValue());
                            }

                            public final KeylineList invoke(float f114, float f115) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f114, density2.mo406toPx0680j_4(f), f115);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i1110 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function115 = function5;
                    Modifier modifier113 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation110, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier113, f113, targetedFlingBehavior2, function115, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i1110) | (3670016 & i1110) | (i1110 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier113;
                    f3 = f113;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                            CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            paddingValues2 = paddingValues;
            if ((i2 & 64) != 0) {
                if ((i & 1572864) == 0) {
                    function5 = function4;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i8 = 1048576;
                    } else {
                        i8 = 524288;
                    }
                    i3 |= i8;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f114 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity111 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume111 = composerStartRestartGroup.consume(localDensity111);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume111;
                    Orientation orientation111 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged111 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged111 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f115, Float f116) {
                                return invoke(f115.floatValue(), f116.floatValue());
                            }

                            public final KeylineList invoke(float f115, float f116) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f115, density2.mo406toPx0680j_4(f), f116);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f115, Float f116) {
                                return invoke(f115.floatValue(), f116.floatValue());
                            }

                            public final KeylineList invoke(float f115, float f116) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f115, density2.mo406toPx0680j_4(f), f116);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i1111 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function116 = function5;
                    Modifier modifier114 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation111, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier114, f114, targetedFlingBehavior2, function116, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i1111) | (3670016 & i1111) | (i1111 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier114;
                    f3 = f114;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f115 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity112 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume112 = composerStartRestartGroup.consume(localDensity112);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume112;
                    Orientation orientation112 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged112 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged112 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f116, Float f117) {
                                return invoke(f116.floatValue(), f117.floatValue());
                            }

                            public final KeylineList invoke(float f116, float f117) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f116, density2.mo406toPx0680j_4(f), f117);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f116, Float f117) {
                                return invoke(f116.floatValue(), f117.floatValue());
                            }

                            public final KeylineList invoke(float f116, float f117) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f116, density2.mo406toPx0680j_4(f), f117);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i1112 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function117 = function5;
                    Modifier modifier115 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation112, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier115, f115, targetedFlingBehavior2, function117, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i1112) | (3670016 & i1112) | (i1112 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier115;
                    f3 = f115;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                            CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            function5 = function4;
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                }
                float f116 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                }
                ProvidableCompositionLocal<Density> localDensity113 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume113 = composerStartRestartGroup.consume(localDensity113);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume113;
                Orientation orientation113 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged113 = composerStartRestartGroup.changed(density);
                if ((i3 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = zChanged113 | z;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f117, Float f118) {
                            return invoke(f117.floatValue(), f118.floatValue());
                        }

                        public final KeylineList invoke(float f117, float f118) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f117, density2.mo406toPx0680j_4(f), f118);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f117, Float f118) {
                            return invoke(f117.floatValue(), f118.floatValue());
                        }

                        public final KeylineList invoke(float f117, float f118) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f117, density2.mo406toPx0680j_4(f), f118);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i1113 = i3 << 9;
                composer2 = composerStartRestartGroup;
                Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function118 = function5;
                Modifier modifier116 = modifier2;
                m2601CarouselV95POc(carouselState2, orientation113, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier116, f116, targetedFlingBehavior2, function118, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i1113) | (3670016 & i1113) | (i1113 & 29360128) | ((i3 << 6) & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier3 = modifier116;
                f3 = f116;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                }
                float f117 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                }
                ProvidableCompositionLocal<Density> localDensity114 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume114 = composerStartRestartGroup.consume(localDensity114);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume114;
                Orientation orientation114 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged114 = composerStartRestartGroup.changed(density);
                if ((i3 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = zChanged114 | z;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f118, Float f119) {
                            return invoke(f118.floatValue(), f119.floatValue());
                        }

                        public final KeylineList invoke(float f118, float f119) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f118, density2.mo406toPx0680j_4(f), f119);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f118, Float f119) {
                            return invoke(f118.floatValue(), f119.floatValue());
                        }

                        public final KeylineList invoke(float f118, float f119) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f118, density2.mo406toPx0680j_4(f), f119);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i1114 = i3 << 9;
                composer2 = composerStartRestartGroup;
                Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function119 = function5;
                Modifier modifier117 = modifier2;
                m2601CarouselV95POc(carouselState2, orientation114, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier117, f117, targetedFlingBehavior2, function119, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i1114) | (3670016 & i1114) | (i1114 & 29360128) | ((i3 << 6) & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier3 = modifier117;
                f3 = f117;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                        CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        fM6890constructorimpl = f2;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
                if (composerStartRestartGroup.changed(targetedFlingBehaviorNoSnapFlingBehavior)) {
                }
                i3 |= i10;
            } else {
                targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
            }
            i3 |= i10;
        } else {
            targetedFlingBehaviorNoSnapFlingBehavior = targetedFlingBehavior;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                paddingValues2 = paddingValues;
                if (composerStartRestartGroup.changed(paddingValues2)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((i2 & 64) != 0) {
                if ((i & 1572864) == 0) {
                    function5 = function4;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i8 = 1048576;
                    } else {
                        i8 = 524288;
                    }
                    i3 |= i8;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f118 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity115 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume115 = composerStartRestartGroup.consume(localDensity115);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume115;
                    Orientation orientation115 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged115 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged115 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f119, Float f1110) {
                                return invoke(f119.floatValue(), f1110.floatValue());
                            }

                            public final KeylineList invoke(float f119, float f1110) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f119, density2.mo406toPx0680j_4(f), f1110);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f119, Float f1110) {
                                return invoke(f119.floatValue(), f1110.floatValue());
                            }

                            public final KeylineList invoke(float f119, float f1110) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f119, density2.mo406toPx0680j_4(f), f1110);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i1115 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function1110 = function5;
                    Modifier modifier118 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation115, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier118, f118, targetedFlingBehavior2, function1110, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i1115) | (3670016 & i1115) | (i1115 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier118;
                    f3 = f118;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                            paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                        }
                    }
                    float f119 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                    }
                    ProvidableCompositionLocal<Density> localDensity116 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume116 = composerStartRestartGroup.consume(localDensity116);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume116;
                    Orientation orientation116 = Orientation.Horizontal;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                    boolean zChanged116 = composerStartRestartGroup.changed(density);
                    if ((i3 & 112) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChanged116 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z2) {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1110, Float f1111) {
                                return invoke(f1110.floatValue(), f1111.floatValue());
                            }

                            public final KeylineList invoke(float f1110, float f1111) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f1110, density2.mo406toPx0680j_4(f), f1111);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ KeylineList invoke(Float f1110, Float f1111) {
                                return invoke(f1110.floatValue(), f1111.floatValue());
                            }

                            public final KeylineList invoke(float f1110, float f1111) {
                                Density density2 = density;
                                return KeylinesKt.uncontainedKeylineList(density2, f1110, density2.mo406toPx0680j_4(f), f1111);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i1116 = i3 << 9;
                    composer2 = composerStartRestartGroup;
                    Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function1111 = function5;
                    Modifier modifier119 = modifier2;
                    m2601CarouselV95POc(carouselState2, orientation116, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier119, f119, targetedFlingBehavior2, function1111, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i1116) | (3670016 & i1116) | (i1116 & 29360128) | ((i3 << 6) & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                    modifier3 = modifier119;
                    f3 = f119;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                            CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            function5 = function4;
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                }
                float f1110 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                }
                ProvidableCompositionLocal<Density> localDensity117 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume117 = composerStartRestartGroup.consume(localDensity117);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume117;
                Orientation orientation117 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged117 = composerStartRestartGroup.changed(density);
                if ((i3 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = zChanged117 | z;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111, Float f1112) {
                            return invoke(f1111.floatValue(), f1112.floatValue());
                        }

                        public final KeylineList invoke(float f1111, float f1112) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f1111, density2.mo406toPx0680j_4(f), f1112);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1111, Float f1112) {
                            return invoke(f1111.floatValue(), f1112.floatValue());
                        }

                        public final KeylineList invoke(float f1111, float f1112) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f1111, density2.mo406toPx0680j_4(f), f1112);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i1117 = i3 << 9;
                composer2 = composerStartRestartGroup;
                Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function1112 = function5;
                Modifier modifier1110 = modifier2;
                m2601CarouselV95POc(carouselState2, orientation117, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier1110, f1110, targetedFlingBehavior2, function1112, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i1117) | (3670016 & i1117) | (i1117 & 29360128) | ((i3 << 6) & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier3 = modifier1110;
                f3 = f1110;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                }
                float f1111 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                }
                ProvidableCompositionLocal<Density> localDensity118 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume118 = composerStartRestartGroup.consume(localDensity118);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume118;
                Orientation orientation118 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged118 = composerStartRestartGroup.changed(density);
                if ((i3 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = zChanged118 | z;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1112, Float f1113) {
                            return invoke(f1112.floatValue(), f1113.floatValue());
                        }

                        public final KeylineList invoke(float f1112, float f1113) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f1112, density2.mo406toPx0680j_4(f), f1113);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1112, Float f1113) {
                            return invoke(f1112.floatValue(), f1113.floatValue());
                        }

                        public final KeylineList invoke(float f1112, float f1113) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f1112, density2.mo406toPx0680j_4(f), f1113);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i1118 = i3 << 9;
                composer2 = composerStartRestartGroup;
                Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function1113 = function5;
                Modifier modifier1111 = modifier2;
                m2601CarouselV95POc(carouselState2, orientation118, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier1111, f1111, targetedFlingBehavior2, function1113, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i1118) | (3670016 & i1118) | (i1118 & 29360128) | ((i3 << 6) & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier3 = modifier1111;
                f3 = f1111;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                        CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        paddingValues2 = paddingValues;
        if ((i2 & 64) != 0) {
            if ((i & 1572864) == 0) {
                function5 = function4;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i8 = 1048576;
                } else {
                    i8 = 524288;
                }
                i3 |= i8;
            }
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                }
                float f1112 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                }
                ProvidableCompositionLocal<Density> localDensity119 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume119 = composerStartRestartGroup.consume(localDensity119);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume119;
                Orientation orientation119 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged119 = composerStartRestartGroup.changed(density);
                if ((i3 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = zChanged119 | z;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1113, Float f1114) {
                            return invoke(f1113.floatValue(), f1114.floatValue());
                        }

                        public final KeylineList invoke(float f1113, float f1114) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f1113, density2.mo406toPx0680j_4(f), f1114);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1113, Float f1114) {
                            return invoke(f1113.floatValue(), f1114.floatValue());
                        }

                        public final KeylineList invoke(float f1113, float f1114) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f1113, density2.mo406toPx0680j_4(f), f1114);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i1119 = i3 << 9;
                composer2 = composerStartRestartGroup;
                Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function1114 = function5;
                Modifier modifier1112 = modifier2;
                m2601CarouselV95POc(carouselState2, orientation119, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier1112, f1112, targetedFlingBehavior2, function1114, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i1119) | (3670016 & i1119) | (i1119 & 29360128) | ((i3 << 6) & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier3 = modifier1112;
                f3 = f1112;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                        paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                    }
                }
                float f1113 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
                }
                ProvidableCompositionLocal<Density> localDensity1110 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1110 = composerStartRestartGroup.consume(localDensity1110);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume1110;
                Orientation orientation1110 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
                boolean zChanged1110 = composerStartRestartGroup.changed(density);
                if ((i3 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = zChanged1110 | z;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z2) {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1114, Float f1115) {
                            return invoke(f1114.floatValue(), f1115.floatValue());
                        }

                        public final KeylineList invoke(float f1114, float f1115) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f1114, density2.mo406toPx0680j_4(f), f1115);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ KeylineList invoke(Float f1114, Float f1115) {
                            return invoke(f1114.floatValue(), f1115.floatValue());
                        }

                        public final KeylineList invoke(float f1114, float f1115) {
                            Density density2 = density;
                            return KeylinesKt.uncontainedKeylineList(density2, f1114, density2.mo406toPx0680j_4(f), f1115);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i11110 = i3 << 9;
                composer2 = composerStartRestartGroup;
                Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function1115 = function5;
                Modifier modifier1113 = modifier2;
                m2601CarouselV95POc(carouselState2, orientation1110, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier1113, f1113, targetedFlingBehavior2, function1115, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i11110) | (3670016 & i11110) | (i11110 & 29360128) | ((i3 << 6) & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                paddingValues3 = paddingValuesM713PaddingValues0680j_4;
                modifier3 = modifier1113;
                f3 = f1113;
                targetedFlingBehavior3 = targetedFlingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                        CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 1572864;
        function5 = function4;
        if ((599187 & i3) == 599186) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                } else {
                    targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
            } else {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                } else {
                    targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
            }
            float f1114 = fM6890constructorimpl;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
            }
            ProvidableCompositionLocal<Density> localDensity1111 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume1111 = composerStartRestartGroup.consume(localDensity1111);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            density = (Density) objConsume1111;
            Orientation orientation1111 = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
            boolean zChanged1111 = composerStartRestartGroup.changed(density);
            if ((i3 & 112) == 32) {
                z = true;
            } else {
                z = false;
            }
            z2 = zChanged1111 | z;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z2) {
                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f1115, Float f1116) {
                        return invoke(f1115.floatValue(), f1116.floatValue());
                    }

                    public final KeylineList invoke(float f1115, float f1116) {
                        Density density2 = density;
                        return KeylinesKt.uncontainedKeylineList(density2, f1115, density2.mo406toPx0680j_4(f), f1116);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f1115, Float f1116) {
                        return invoke(f1115.floatValue(), f1116.floatValue());
                    }

                    public final KeylineList invoke(float f1115, float f1116) {
                        Density density2 = density;
                        return KeylinesKt.uncontainedKeylineList(density2, f1115, density2.mo406toPx0680j_4(f), f1116);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i11111 = i3 << 9;
            composer2 = composerStartRestartGroup;
            Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function1116 = function5;
            Modifier modifier1114 = modifier2;
            m2601CarouselV95POc(carouselState2, orientation1111, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier1114, f1114, targetedFlingBehavior2, function1116, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i11111) | (3670016 & i11111) | (i11111 & 29360128) | ((i3 << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
            modifier3 = modifier1114;
            f3 = f1114;
            targetedFlingBehavior3 = targetedFlingBehavior2;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                } else {
                    targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
            } else {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    targetedFlingBehaviorNoSnapFlingBehavior = CarouselDefaults.INSTANCE.noSnapFlingBehavior(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    paddingValuesM713PaddingValues0680j_4 = PaddingKt.m713PaddingValues0680j_4(Dp.m6890constructorimpl(0.0f));
                    targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                } else {
                    targetedFlingBehavior2 = targetedFlingBehaviorNoSnapFlingBehavior;
                    paddingValuesM713PaddingValues0680j_4 = paddingValues2;
                }
            }
            float f1115 = fM6890constructorimpl;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(529322840, i3, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
            }
            ProvidableCompositionLocal<Density> localDensity1112 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume1112 = composerStartRestartGroup.consume(localDensity1112);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            density = (Density) objConsume1112;
            Orientation orientation1112 = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1883505148, "CC(remember):Carousel.kt#9igjgp");
            boolean zChanged1112 = composerStartRestartGroup.changed(density);
            if ((i3 & 112) == 32) {
                z = true;
            } else {
                z = false;
            }
            z2 = zChanged1112 | z;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z2) {
                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f1116, Float f1117) {
                        return invoke(f1116.floatValue(), f1117.floatValue());
                    }

                    public final KeylineList invoke(float f1116, float f1117) {
                        Density density2 = density;
                        return KeylinesKt.uncontainedKeylineList(density2, f1116, density2.mo406toPx0680j_4(f), f1117);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f1116, Float f1117) {
                        return invoke(f1116.floatValue(), f1117.floatValue());
                    }

                    public final KeylineList invoke(float f1116, float f1117) {
                        Density density2 = density;
                        return KeylinesKt.uncontainedKeylineList(density2, f1116, density2.mo406toPx0680j_4(f), f1117);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i11112 = i3 << 9;
            composer2 = composerStartRestartGroup;
            Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function1117 = function5;
            Modifier modifier1115 = modifier2;
            m2601CarouselV95POc(carouselState2, orientation1112, (Function2) objRememberedValue, paddingValuesM713PaddingValues0680j_4, 0, modifier1115, f1115, targetedFlingBehavior2, function1117, composer2, (i3 & 14) | 24624 | ((i3 >> 6) & 7168) | (458752 & i11112) | (3670016 & i11112) | (i11112 & 29360128) | ((i3 << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            paddingValues3 = paddingValuesM713PaddingValues0680j_4;
            modifier3 = modifier1115;
            f3 = f1115;
            targetedFlingBehavior3 = targetedFlingBehavior2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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
                    CarouselKt.m2603HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier3, f3, targetedFlingBehavior3, paddingValues3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x011d  */
    /* JADX WARN: Code duplicated, block: B:104:0x0127  */
    /* JADX WARN: Code duplicated, block: B:112:0x013e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x0140  */
    /* JADX WARN: Code duplicated, block: B:114:0x0147  */
    /* JADX WARN: Code duplicated, block: B:116:0x014b  */
    /* JADX WARN: Code duplicated, block: B:119:0x0155  */
    /* JADX WARN: Code duplicated, block: B:120:0x0168  */
    /* JADX WARN: Code duplicated, block: B:124:0x0178  */
    /* JADX WARN: Code duplicated, block: B:127:0x019f  */
    /* JADX WARN: Code duplicated, block: B:128:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:131:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:133:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:136:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:137:0x0233  */
    /* JADX WARN: Code duplicated, block: B:139:0x023b  */
    /* JADX WARN: Code duplicated, block: B:140:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:143:0x02db  */
    /* JADX WARN: Code duplicated, block: B:147:0x02e9  */
    /* JADX WARN: Code duplicated, block: B:149:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:69:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:81:0x00de  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:93:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:94:0x0100  */
    /* JADX WARN: Code duplicated, block: B:98:0x010d  */
    /* JADX INFO: renamed from: Carousel-V-95POc, reason: not valid java name */
    public static final void m2601CarouselV95POc(CarouselState carouselState, final Orientation orientation, final Function2<? super Float, ? super Float, KeylineList> function2, final PaddingValues paddingValues, final int i, Modifier modifier, float f, TargetedFlingBehavior targetedFlingBehavior, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i2, final int i3) {
        int i4;
        int i5;
        Modifier modifier2;
        int i6;
        float fM6890constructorimpl;
        int i7;
        TargetedFlingBehavior targetedFlingBehavior2;
        int i8;
        int i9;
        Modifier modifier3;
        final CarouselState carouselState2;
        TargetedFlingBehavior targetedFlingBehaviorSingleAdvanceFlingBehavior;
        float f2;
        float fCalculateBeforeContentPadding;
        float fCalculateAfterContentPadding;
        boolean z;
        Object objRememberedValue;
        final CarouselPageSize carouselPageSize;
        SnapPosition snapPositionKeylineSnapPosition;
        Modifier modifier4;
        final float f3;
        final TargetedFlingBehavior targetedFlingBehavior3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2035733443);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Carousel)P(8,7,4,1,5,6,3:c#ui.unit.Dp,2)239@11411L41,242@11572L42,243@11660L41,245@11729L118:Carousel.kt#dcf9yb");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(carouselState) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(orientation) ? 32 : 16;
        }
        if ((i3 & 4) != 0) {
            i4 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i3 & 8) != 0) {
            i4 |= 3072;
        } else if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changed(paddingValues) ? 2048 : 1024;
        }
        if ((i3 & 16) != 0) {
            i4 |= 24576;
            i5 = i;
        } else {
            i5 = i;
            if ((i2 & 24576) == 0) {
                i4 |= composerStartRestartGroup.changed(i5) ? 16384 : 8192;
            }
        }
        int i10 = i3 & 32;
        if (i10 == 0) {
            if ((196608 & i2) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 131072 : 65536;
            }
            i6 = i3 & 64;
            if (i6 != 0) {
                if ((1572864 & i2) == 0) {
                    fM6890constructorimpl = f;
                    if (composerStartRestartGroup.changed(fM6890constructorimpl)) {
                        i7 = 1048576;
                    } else {
                        i7 = 524288;
                    }
                    i4 |= i7;
                }
                if ((i2 & 12582912) == 0) {
                    if ((i3 & 128) == 0) {
                        targetedFlingBehavior2 = targetedFlingBehavior;
                        int i11 = composerStartRestartGroup.changed(targetedFlingBehavior2) ? 8388608 : 4194304;
                        i4 |= i11;
                    } else {
                        targetedFlingBehavior2 = targetedFlingBehavior;
                    }
                    i4 |= i11;
                } else {
                    targetedFlingBehavior2 = targetedFlingBehavior;
                }
                if ((i3 & 256) != 0) {
                    i4 |= 100663296;
                } else if ((i2 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 67108864;
                    } else {
                        i8 = 33554432;
                    }
                    i4 |= i8;
                }
                i9 = i4;
                if ((38347923 & i9) == 38347922 || !composerStartRestartGroup.getSkipping()) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i10 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i6 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        if ((i3 & 128) != 0) {
                            CarouselDefaults carouselDefaults = CarouselDefaults.INSTANCE;
                            int i12 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                            carouselState2 = carouselState;
                            i9 &= -29360129;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i12, 2);
                        } else {
                            carouselState2 = carouselState;
                            targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                        }
                        modifier2 = modifier3;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 128) != 0) {
                            i9 &= -29360129;
                        }
                        carouselState2 = carouselState;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                    }
                    f2 = fM6890constructorimpl;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2035733443, i9, -1, "androidx.compose.material3.carousel.Carousel (Carousel.kt:241)");
                    }
                    int i13 = ((i9 >> 9) & 14) | (i9 & 112);
                    fCalculateBeforeContentPadding = calculateBeforeContentPadding(paddingValues, orientation, composerStartRestartGroup, i13);
                    fCalculateAfterContentPadding = calculateAfterContentPadding(paddingValues, orientation, composerStartRestartGroup, i13);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2025986753, "CC(remember):Carousel.kt#9igjgp");
                    if ((i9 & 896) == 256) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    carouselPageSize = (CarouselPageSize) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    snapPositionKeylineSnapPosition = KeylineSnapPositionKt.KeylineSnapPosition(carouselPageSize);
                    if (orientation == Orientation.Horizontal) {
                        composerStartRestartGroup.startReplaceGroup(-1618653092);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "266@12589L1014,252@11960L1643");
                        int i14 = i5;
                        modifier4 = modifier2;
                        PagerKt.m964HorizontalPageroI3XNZo(carouselState2.getPagerState(), modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(0.0f, paddingValues.getTop(), 0.0f, paddingValues.getBottom(), 5, null), carouselPageSize, i14, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(687111200, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                                invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(PagerScope pagerScope, int i15, Composer composer2, int i16) {
                                ComposerKt.sourceInformation(composer2, "C267@12634L35,268@12694L63,269@12786L389,286@13376L21,281@13189L404:Carousel.kt#dcf9yb");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(687111200, i16, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:267)");
                                }
                                ComposerKt.sourceInformationMarkerStart(composer2, -685906675, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue2 = composer2.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = new CarouselItemInfoImpl();
                                    composer2.updateRememberedValue(objRememberedValue2);
                                }
                                final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -685904727, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue3 = composer2.rememberedValue();
                                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                    composer2.updateRememberedValue(objRememberedValue3);
                                }
                                CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -685901457, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue4 = composer2.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$clipShape$1$1
                                        @Override // androidx.compose.ui.graphics.Shape
                                        /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                        public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                            return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue4);
                                }
                                CarouselKt$Carousel$1$clipShape$1$1 carouselKt$Carousel$1$clipShape$1$1 = (CarouselKt$Carousel$1$clipShape$1$1) objRememberedValue4;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier.Companion companion = Modifier.INSTANCE;
                                CarouselState carouselState3 = carouselState2;
                                ComposerKt.sourceInformationMarkerStart(composer2, -685882945, "CC(remember):Carousel.kt#9igjgp");
                                boolean zChanged = composer2.changed(carouselPageSize);
                                final CarouselPageSize carouselPageSize2 = carouselPageSize;
                                Object objRememberedValue5 = composer2.rememberedValue();
                                if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Strategy invoke() {
                                            return carouselPageSize2.getStrategy();
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue5);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i15, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$1$clipShape$1$1);
                                Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                                ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1112607687, "C291@13566L13:Carousel.kt#dcf9yb");
                                function5.invoke(carouselItemScopeImpl, Integer.valueOf(i15), composer2, Integer.valueOf(i16 & 112));
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        modifier4 = modifier2;
                        if (orientation == Orientation.Vertical) {
                            composerStartRestartGroup.startReplaceGroup(-1616959128);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "300@13962L7,301@14054L7,309@14347L1014,295@13666L1695");
                            PagerState pagerState = carouselState2.getPagerState();
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume = composerStartRestartGroup.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            float fCalculateStartPadding = PaddingKt.calculateStartPadding(paddingValues, (LayoutDirection) objConsume);
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection2);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            PagerKt.m965VerticalPageroI3XNZo(pagerState, modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(fCalculateStartPadding, 0.0f, PaddingKt.calculateEndPadding(paddingValues, (LayoutDirection) objConsume2), 0.0f, 10, null), carouselPageSize, i, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(-817308503, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(4);
                                }

                                @Override // kotlin.jvm.functions.Function4
                                public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                                    invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(PagerScope pagerScope, int i15, Composer composer2, int i16) {
                                    ComposerKt.sourceInformation(composer2, "C310@14392L35,311@14452L63,312@14544L389,329@15134L21,324@14947L404:Carousel.kt#dcf9yb");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-817308503, i16, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:310)");
                                    }
                                    ComposerKt.sourceInformationMarkerStart(composer2, -685850419, "CC(remember):Carousel.kt#9igjgp");
                                    Object objRememberedValue2 = composer2.rememberedValue();
                                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue2 = new CarouselItemInfoImpl();
                                        composer2.updateRememberedValue(objRememberedValue2);
                                    }
                                    final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerStart(composer2, -685848471, "CC(remember):Carousel.kt#9igjgp");
                                    Object objRememberedValue3 = composer2.rememberedValue();
                                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                        composer2.updateRememberedValue(objRememberedValue3);
                                    }
                                    CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerStart(composer2, -685845201, "CC(remember):Carousel.kt#9igjgp");
                                    Object objRememberedValue4 = composer2.rememberedValue();
                                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$clipShape$1$1
                                            @Override // androidx.compose.ui.graphics.Shape
                                            /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                            public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                                return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                            }
                                        };
                                        composer2.updateRememberedValue(objRememberedValue4);
                                    }
                                    CarouselKt$Carousel$2$clipShape$1$1 carouselKt$Carousel$2$clipShape$1$1 = (CarouselKt$Carousel$2$clipShape$1$1) objRememberedValue4;
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    CarouselState carouselState3 = carouselState2;
                                    ComposerKt.sourceInformationMarkerStart(composer2, -685826689, "CC(remember):Carousel.kt#9igjgp");
                                    boolean zChanged = composer2.changed(carouselPageSize);
                                    final CarouselPageSize carouselPageSize2 = carouselPageSize;
                                    Object objRememberedValue5 = composer2.rememberedValue();
                                    if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$1$1
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Strategy invoke() {
                                                return carouselPageSize2.getStrategy();
                                            }
                                        };
                                        composer2.updateRememberedValue(objRememberedValue5);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i15, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$2$clipShape$1$1);
                                    Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                                    ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer2, 1114351623, "C334@15324L13:Carousel.kt#dcf9yb");
                                    function5.invoke(carouselItemScopeImpl, Integer.valueOf(i15), composer2, Integer.valueOf(i16 & 112));
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1615314857);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    f3 = f2;
                    targetedFlingBehavior3 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    carouselState2 = carouselState;
                    targetedFlingBehavior3 = targetedFlingBehavior2;
                    f3 = fM6890constructorimpl;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$3
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
                            CarouselKt.m2601CarouselV95POc(carouselState2, orientation, function2, paddingValues, i, modifier5, f3, targetedFlingBehavior3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 1572864;
            fM6890constructorimpl = f;
            if ((i2 & 12582912) == 0) {
                if ((i3 & 128) == 0) {
                    targetedFlingBehavior2 = targetedFlingBehavior;
                    if (composerStartRestartGroup.changed(targetedFlingBehavior2)) {
                    }
                    i4 |= i11;
                } else {
                    targetedFlingBehavior2 = targetedFlingBehavior;
                }
                i4 |= i11;
            } else {
                targetedFlingBehavior2 = targetedFlingBehavior;
            }
            if ((i3 & 256) != 0) {
                i4 |= 100663296;
            } else if ((i2 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i8 = 67108864;
                } else {
                    i8 = 33554432;
                }
                i4 |= i8;
            }
            i9 = i4;
            if ((38347923 & i9) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i6 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i3 & 128) != 0) {
                        CarouselDefaults carouselDefaults2 = CarouselDefaults.INSTANCE;
                        int i15 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i9 &= -29360129;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults2.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i15, 2);
                    } else {
                        carouselState2 = carouselState;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                    }
                    modifier2 = modifier3;
                } else {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i6 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i3 & 128) != 0) {
                        CarouselDefaults carouselDefaults3 = CarouselDefaults.INSTANCE;
                        int i16 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i9 &= -29360129;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults3.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i16, 2);
                    } else {
                        carouselState2 = carouselState;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                    }
                    modifier2 = modifier3;
                }
                f2 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2035733443, i9, -1, "androidx.compose.material3.carousel.Carousel (Carousel.kt:241)");
                }
                int i17 = ((i9 >> 9) & 14) | (i9 & 112);
                fCalculateBeforeContentPadding = calculateBeforeContentPadding(paddingValues, orientation, composerStartRestartGroup, i17);
                fCalculateAfterContentPadding = calculateAfterContentPadding(paddingValues, orientation, composerStartRestartGroup, i17);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2025986753, "CC(remember):Carousel.kt#9igjgp");
                if ((i9 & 896) == 256) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                carouselPageSize = (CarouselPageSize) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                snapPositionKeylineSnapPosition = KeylineSnapPositionKt.KeylineSnapPosition(carouselPageSize);
                if (orientation == Orientation.Horizontal) {
                    composerStartRestartGroup.startReplaceGroup(-1618653092);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "266@12589L1014,252@11960L1643");
                    int i18 = i5;
                    modifier4 = modifier2;
                    PagerKt.m964HorizontalPageroI3XNZo(carouselState2.getPagerState(), modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(0.0f, paddingValues.getTop(), 0.0f, paddingValues.getBottom(), 5, null), carouselPageSize, i18, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(687111200, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                            invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PagerScope pagerScope, int i19, Composer composer2, int i110) {
                            ComposerKt.sourceInformation(composer2, "C267@12634L35,268@12694L63,269@12786L389,286@13376L21,281@13189L404:Carousel.kt#dcf9yb");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(687111200, i110, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:267)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composer2, -685906675, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue2 = composer2.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = new CarouselItemInfoImpl();
                                composer2.updateRememberedValue(objRememberedValue2);
                            }
                            final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685904727, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue3 = composer2.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                composer2.updateRememberedValue(objRememberedValue3);
                            }
                            CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685901457, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue4 = composer2.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$clipShape$1$1
                                    @Override // androidx.compose.ui.graphics.Shape
                                    /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                    public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                        return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue4);
                            }
                            CarouselKt$Carousel$1$clipShape$1$1 carouselKt$Carousel$1$clipShape$1$1 = (CarouselKt$Carousel$1$clipShape$1$1) objRememberedValue4;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier.Companion companion = Modifier.INSTANCE;
                            CarouselState carouselState3 = carouselState2;
                            ComposerKt.sourceInformationMarkerStart(composer2, -685882945, "CC(remember):Carousel.kt#9igjgp");
                            boolean zChanged = composer2.changed(carouselPageSize);
                            final CarouselPageSize carouselPageSize2 = carouselPageSize;
                            Object objRememberedValue5 = composer2.rememberedValue();
                            if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$1$1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Strategy invoke() {
                                        return carouselPageSize2.getStrategy();
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue5);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i19, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$1$clipShape$1$1);
                            Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                            ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1112607687, "C291@13566L13:Carousel.kt#dcf9yb");
                            function5.invoke(carouselItemScopeImpl, Integer.valueOf(i19), composer2, Integer.valueOf(i110 & 112));
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    modifier4 = modifier2;
                    if (orientation == Orientation.Vertical) {
                        composerStartRestartGroup.startReplaceGroup(-1616959128);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "300@13962L7,301@14054L7,309@14347L1014,295@13666L1695");
                        PagerState pagerState2 = carouselState2.getPagerState();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = composerStartRestartGroup.consume(localLayoutDirection3);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        float fCalculateStartPadding2 = PaddingKt.calculateStartPadding(paddingValues, (LayoutDirection) objConsume3);
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = composerStartRestartGroup.consume(localLayoutDirection4);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        PagerKt.m965VerticalPageroI3XNZo(pagerState2, modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(fCalculateStartPadding2, 0.0f, PaddingKt.calculateEndPadding(paddingValues, (LayoutDirection) objConsume4), 0.0f, 10, null), carouselPageSize, i, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(-817308503, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                                invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(PagerScope pagerScope, int i19, Composer composer2, int i110) {
                                ComposerKt.sourceInformation(composer2, "C310@14392L35,311@14452L63,312@14544L389,329@15134L21,324@14947L404:Carousel.kt#dcf9yb");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-817308503, i110, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:310)");
                                }
                                ComposerKt.sourceInformationMarkerStart(composer2, -685850419, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue2 = composer2.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = new CarouselItemInfoImpl();
                                    composer2.updateRememberedValue(objRememberedValue2);
                                }
                                final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -685848471, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue3 = composer2.rememberedValue();
                                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                    composer2.updateRememberedValue(objRememberedValue3);
                                }
                                CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -685845201, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue4 = composer2.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$clipShape$1$1
                                        @Override // androidx.compose.ui.graphics.Shape
                                        /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                        public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                            return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue4);
                                }
                                CarouselKt$Carousel$2$clipShape$1$1 carouselKt$Carousel$2$clipShape$1$1 = (CarouselKt$Carousel$2$clipShape$1$1) objRememberedValue4;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier.Companion companion = Modifier.INSTANCE;
                                CarouselState carouselState3 = carouselState2;
                                ComposerKt.sourceInformationMarkerStart(composer2, -685826689, "CC(remember):Carousel.kt#9igjgp");
                                boolean zChanged = composer2.changed(carouselPageSize);
                                final CarouselPageSize carouselPageSize2 = carouselPageSize;
                                Object objRememberedValue5 = composer2.rememberedValue();
                                if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Strategy invoke() {
                                            return carouselPageSize2.getStrategy();
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue5);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i19, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$2$clipShape$1$1);
                                Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                                ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1114351623, "C334@15324L13:Carousel.kt#dcf9yb");
                                function5.invoke(carouselItemScopeImpl, Integer.valueOf(i19), composer2, Integer.valueOf(i110 & 112));
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1615314857);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                f3 = f2;
                targetedFlingBehavior3 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i6 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i3 & 128) != 0) {
                        CarouselDefaults carouselDefaults4 = CarouselDefaults.INSTANCE;
                        int i19 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i9 &= -29360129;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults4.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i19, 2);
                    } else {
                        carouselState2 = carouselState;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                    }
                    modifier2 = modifier3;
                } else {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i6 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i3 & 128) != 0) {
                        CarouselDefaults carouselDefaults5 = CarouselDefaults.INSTANCE;
                        int i110 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i9 &= -29360129;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults5.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i110, 2);
                    } else {
                        carouselState2 = carouselState;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                    }
                    modifier2 = modifier3;
                }
                f2 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2035733443, i9, -1, "androidx.compose.material3.carousel.Carousel (Carousel.kt:241)");
                }
                int i111 = ((i9 >> 9) & 14) | (i9 & 112);
                fCalculateBeforeContentPadding = calculateBeforeContentPadding(paddingValues, orientation, composerStartRestartGroup, i111);
                fCalculateAfterContentPadding = calculateAfterContentPadding(paddingValues, orientation, composerStartRestartGroup, i111);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2025986753, "CC(remember):Carousel.kt#9igjgp");
                if ((i9 & 896) == 256) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                carouselPageSize = (CarouselPageSize) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                snapPositionKeylineSnapPosition = KeylineSnapPositionKt.KeylineSnapPosition(carouselPageSize);
                if (orientation == Orientation.Horizontal) {
                    composerStartRestartGroup.startReplaceGroup(-1618653092);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "266@12589L1014,252@11960L1643");
                    int i112 = i5;
                    modifier4 = modifier2;
                    PagerKt.m964HorizontalPageroI3XNZo(carouselState2.getPagerState(), modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(0.0f, paddingValues.getTop(), 0.0f, paddingValues.getBottom(), 5, null), carouselPageSize, i112, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(687111200, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                            invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PagerScope pagerScope, int i113, Composer composer2, int i114) {
                            ComposerKt.sourceInformation(composer2, "C267@12634L35,268@12694L63,269@12786L389,286@13376L21,281@13189L404:Carousel.kt#dcf9yb");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(687111200, i114, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:267)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composer2, -685906675, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue2 = composer2.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = new CarouselItemInfoImpl();
                                composer2.updateRememberedValue(objRememberedValue2);
                            }
                            final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685904727, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue3 = composer2.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                composer2.updateRememberedValue(objRememberedValue3);
                            }
                            CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685901457, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue4 = composer2.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$clipShape$1$1
                                    @Override // androidx.compose.ui.graphics.Shape
                                    /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                    public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                        return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue4);
                            }
                            CarouselKt$Carousel$1$clipShape$1$1 carouselKt$Carousel$1$clipShape$1$1 = (CarouselKt$Carousel$1$clipShape$1$1) objRememberedValue4;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier.Companion companion = Modifier.INSTANCE;
                            CarouselState carouselState3 = carouselState2;
                            ComposerKt.sourceInformationMarkerStart(composer2, -685882945, "CC(remember):Carousel.kt#9igjgp");
                            boolean zChanged = composer2.changed(carouselPageSize);
                            final CarouselPageSize carouselPageSize2 = carouselPageSize;
                            Object objRememberedValue5 = composer2.rememberedValue();
                            if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$1$1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Strategy invoke() {
                                        return carouselPageSize2.getStrategy();
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue5);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i113, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$1$clipShape$1$1);
                            Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                            ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1112607687, "C291@13566L13:Carousel.kt#dcf9yb");
                            function5.invoke(carouselItemScopeImpl, Integer.valueOf(i113), composer2, Integer.valueOf(i114 & 112));
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    modifier4 = modifier2;
                    if (orientation == Orientation.Vertical) {
                        composerStartRestartGroup.startReplaceGroup(-1616959128);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "300@13962L7,301@14054L7,309@14347L1014,295@13666L1695");
                        PagerState pagerState3 = carouselState2.getPagerState();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection5 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume5 = composerStartRestartGroup.consume(localLayoutDirection5);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        float fCalculateStartPadding3 = PaddingKt.calculateStartPadding(paddingValues, (LayoutDirection) objConsume5);
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection6 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume6 = composerStartRestartGroup.consume(localLayoutDirection6);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        PagerKt.m965VerticalPageroI3XNZo(pagerState3, modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(fCalculateStartPadding3, 0.0f, PaddingKt.calculateEndPadding(paddingValues, (LayoutDirection) objConsume6), 0.0f, 10, null), carouselPageSize, i, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(-817308503, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                                invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(PagerScope pagerScope, int i113, Composer composer2, int i114) {
                                ComposerKt.sourceInformation(composer2, "C310@14392L35,311@14452L63,312@14544L389,329@15134L21,324@14947L404:Carousel.kt#dcf9yb");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-817308503, i114, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:310)");
                                }
                                ComposerKt.sourceInformationMarkerStart(composer2, -685850419, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue2 = composer2.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = new CarouselItemInfoImpl();
                                    composer2.updateRememberedValue(objRememberedValue2);
                                }
                                final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -685848471, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue3 = composer2.rememberedValue();
                                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                    composer2.updateRememberedValue(objRememberedValue3);
                                }
                                CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -685845201, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue4 = composer2.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$clipShape$1$1
                                        @Override // androidx.compose.ui.graphics.Shape
                                        /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                        public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                            return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue4);
                                }
                                CarouselKt$Carousel$2$clipShape$1$1 carouselKt$Carousel$2$clipShape$1$1 = (CarouselKt$Carousel$2$clipShape$1$1) objRememberedValue4;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier.Companion companion = Modifier.INSTANCE;
                                CarouselState carouselState3 = carouselState2;
                                ComposerKt.sourceInformationMarkerStart(composer2, -685826689, "CC(remember):Carousel.kt#9igjgp");
                                boolean zChanged = composer2.changed(carouselPageSize);
                                final CarouselPageSize carouselPageSize2 = carouselPageSize;
                                Object objRememberedValue5 = composer2.rememberedValue();
                                if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Strategy invoke() {
                                            return carouselPageSize2.getStrategy();
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue5);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i113, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$2$clipShape$1$1);
                                Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                                ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1114351623, "C334@15324L13:Carousel.kt#dcf9yb");
                                function5.invoke(carouselItemScopeImpl, Integer.valueOf(i113), composer2, Integer.valueOf(i114 & 112));
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1615314857);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                f3 = f2;
                targetedFlingBehavior3 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$3
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

                    public final void invoke(Composer composer2, int i113) {
                        CarouselKt.m2601CarouselV95POc(carouselState2, orientation, function2, paddingValues, i, modifier6, f3, targetedFlingBehavior3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        modifier2 = modifier;
        i6 = i3 & 64;
        if (i6 != 0) {
            if ((1572864 & i2) == 0) {
                fM6890constructorimpl = f;
                if (composerStartRestartGroup.changed(fM6890constructorimpl)) {
                    i7 = 1048576;
                } else {
                    i7 = 524288;
                }
                i4 |= i7;
            }
            if ((i2 & 12582912) == 0) {
                if ((i3 & 128) == 0) {
                    targetedFlingBehavior2 = targetedFlingBehavior;
                    if (composerStartRestartGroup.changed(targetedFlingBehavior2)) {
                    }
                    i4 |= i11;
                } else {
                    targetedFlingBehavior2 = targetedFlingBehavior;
                }
                i4 |= i11;
            } else {
                targetedFlingBehavior2 = targetedFlingBehavior;
            }
            if ((i3 & 256) != 0) {
                i4 |= 100663296;
            } else if ((i2 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i8 = 67108864;
                } else {
                    i8 = 33554432;
                }
                i4 |= i8;
            }
            i9 = i4;
            if ((38347923 & i9) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i6 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i3 & 128) != 0) {
                        CarouselDefaults carouselDefaults6 = CarouselDefaults.INSTANCE;
                        int i113 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i9 &= -29360129;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults6.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i113, 2);
                    } else {
                        carouselState2 = carouselState;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                    }
                    modifier2 = modifier3;
                } else {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i6 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i3 & 128) != 0) {
                        CarouselDefaults carouselDefaults7 = CarouselDefaults.INSTANCE;
                        int i114 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i9 &= -29360129;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults7.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i114, 2);
                    } else {
                        carouselState2 = carouselState;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                    }
                    modifier2 = modifier3;
                }
                f2 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2035733443, i9, -1, "androidx.compose.material3.carousel.Carousel (Carousel.kt:241)");
                }
                int i115 = ((i9 >> 9) & 14) | (i9 & 112);
                fCalculateBeforeContentPadding = calculateBeforeContentPadding(paddingValues, orientation, composerStartRestartGroup, i115);
                fCalculateAfterContentPadding = calculateAfterContentPadding(paddingValues, orientation, composerStartRestartGroup, i115);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2025986753, "CC(remember):Carousel.kt#9igjgp");
                if ((i9 & 896) == 256) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                carouselPageSize = (CarouselPageSize) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                snapPositionKeylineSnapPosition = KeylineSnapPositionKt.KeylineSnapPosition(carouselPageSize);
                if (orientation == Orientation.Horizontal) {
                    composerStartRestartGroup.startReplaceGroup(-1618653092);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "266@12589L1014,252@11960L1643");
                    int i116 = i5;
                    modifier4 = modifier2;
                    PagerKt.m964HorizontalPageroI3XNZo(carouselState2.getPagerState(), modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(0.0f, paddingValues.getTop(), 0.0f, paddingValues.getBottom(), 5, null), carouselPageSize, i116, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(687111200, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                            invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PagerScope pagerScope, int i117, Composer composer2, int i118) {
                            ComposerKt.sourceInformation(composer2, "C267@12634L35,268@12694L63,269@12786L389,286@13376L21,281@13189L404:Carousel.kt#dcf9yb");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(687111200, i118, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:267)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composer2, -685906675, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue2 = composer2.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = new CarouselItemInfoImpl();
                                composer2.updateRememberedValue(objRememberedValue2);
                            }
                            final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685904727, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue3 = composer2.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                composer2.updateRememberedValue(objRememberedValue3);
                            }
                            CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685901457, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue4 = composer2.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$clipShape$1$1
                                    @Override // androidx.compose.ui.graphics.Shape
                                    /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                    public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                        return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue4);
                            }
                            CarouselKt$Carousel$1$clipShape$1$1 carouselKt$Carousel$1$clipShape$1$1 = (CarouselKt$Carousel$1$clipShape$1$1) objRememberedValue4;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier.Companion companion = Modifier.INSTANCE;
                            CarouselState carouselState3 = carouselState2;
                            ComposerKt.sourceInformationMarkerStart(composer2, -685882945, "CC(remember):Carousel.kt#9igjgp");
                            boolean zChanged = composer2.changed(carouselPageSize);
                            final CarouselPageSize carouselPageSize2 = carouselPageSize;
                            Object objRememberedValue5 = composer2.rememberedValue();
                            if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$1$1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Strategy invoke() {
                                        return carouselPageSize2.getStrategy();
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue5);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i117, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$1$clipShape$1$1);
                            Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                            ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1112607687, "C291@13566L13:Carousel.kt#dcf9yb");
                            function5.invoke(carouselItemScopeImpl, Integer.valueOf(i117), composer2, Integer.valueOf(i118 & 112));
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    modifier4 = modifier2;
                    if (orientation == Orientation.Vertical) {
                        composerStartRestartGroup.startReplaceGroup(-1616959128);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "300@13962L7,301@14054L7,309@14347L1014,295@13666L1695");
                        PagerState pagerState4 = carouselState2.getPagerState();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection7 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume7 = composerStartRestartGroup.consume(localLayoutDirection7);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        float fCalculateStartPadding4 = PaddingKt.calculateStartPadding(paddingValues, (LayoutDirection) objConsume7);
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection8 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume8 = composerStartRestartGroup.consume(localLayoutDirection8);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        PagerKt.m965VerticalPageroI3XNZo(pagerState4, modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(fCalculateStartPadding4, 0.0f, PaddingKt.calculateEndPadding(paddingValues, (LayoutDirection) objConsume8), 0.0f, 10, null), carouselPageSize, i, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(-817308503, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                                invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(PagerScope pagerScope, int i117, Composer composer2, int i118) {
                                ComposerKt.sourceInformation(composer2, "C310@14392L35,311@14452L63,312@14544L389,329@15134L21,324@14947L404:Carousel.kt#dcf9yb");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-817308503, i118, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:310)");
                                }
                                ComposerKt.sourceInformationMarkerStart(composer2, -685850419, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue2 = composer2.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = new CarouselItemInfoImpl();
                                    composer2.updateRememberedValue(objRememberedValue2);
                                }
                                final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -685848471, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue3 = composer2.rememberedValue();
                                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                    composer2.updateRememberedValue(objRememberedValue3);
                                }
                                CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -685845201, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue4 = composer2.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$clipShape$1$1
                                        @Override // androidx.compose.ui.graphics.Shape
                                        /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                        public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                            return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue4);
                                }
                                CarouselKt$Carousel$2$clipShape$1$1 carouselKt$Carousel$2$clipShape$1$1 = (CarouselKt$Carousel$2$clipShape$1$1) objRememberedValue4;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier.Companion companion = Modifier.INSTANCE;
                                CarouselState carouselState3 = carouselState2;
                                ComposerKt.sourceInformationMarkerStart(composer2, -685826689, "CC(remember):Carousel.kt#9igjgp");
                                boolean zChanged = composer2.changed(carouselPageSize);
                                final CarouselPageSize carouselPageSize2 = carouselPageSize;
                                Object objRememberedValue5 = composer2.rememberedValue();
                                if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Strategy invoke() {
                                            return carouselPageSize2.getStrategy();
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue5);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i117, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$2$clipShape$1$1);
                                Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                                ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1114351623, "C334@15324L13:Carousel.kt#dcf9yb");
                                function5.invoke(carouselItemScopeImpl, Integer.valueOf(i117), composer2, Integer.valueOf(i118 & 112));
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1615314857);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                f3 = f2;
                targetedFlingBehavior3 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i6 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i3 & 128) != 0) {
                        CarouselDefaults carouselDefaults8 = CarouselDefaults.INSTANCE;
                        int i117 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i9 &= -29360129;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults8.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i117, 2);
                    } else {
                        carouselState2 = carouselState;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                    }
                    modifier2 = modifier3;
                } else {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i6 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    if ((i3 & 128) != 0) {
                        CarouselDefaults carouselDefaults9 = CarouselDefaults.INSTANCE;
                        int i118 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                        carouselState2 = carouselState;
                        i9 &= -29360129;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults9.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i118, 2);
                    } else {
                        carouselState2 = carouselState;
                        targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                    }
                    modifier2 = modifier3;
                }
                f2 = fM6890constructorimpl;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2035733443, i9, -1, "androidx.compose.material3.carousel.Carousel (Carousel.kt:241)");
                }
                int i119 = ((i9 >> 9) & 14) | (i9 & 112);
                fCalculateBeforeContentPadding = calculateBeforeContentPadding(paddingValues, orientation, composerStartRestartGroup, i119);
                fCalculateAfterContentPadding = calculateAfterContentPadding(paddingValues, orientation, composerStartRestartGroup, i119);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2025986753, "CC(remember):Carousel.kt#9igjgp");
                if ((i9 & 896) == 256) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                carouselPageSize = (CarouselPageSize) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                snapPositionKeylineSnapPosition = KeylineSnapPositionKt.KeylineSnapPosition(carouselPageSize);
                if (orientation == Orientation.Horizontal) {
                    composerStartRestartGroup.startReplaceGroup(-1618653092);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "266@12589L1014,252@11960L1643");
                    int i1110 = i5;
                    modifier4 = modifier2;
                    PagerKt.m964HorizontalPageroI3XNZo(carouselState2.getPagerState(), modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(0.0f, paddingValues.getTop(), 0.0f, paddingValues.getBottom(), 5, null), carouselPageSize, i1110, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(687111200, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                            invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PagerScope pagerScope, int i1111, Composer composer2, int i1112) {
                            ComposerKt.sourceInformation(composer2, "C267@12634L35,268@12694L63,269@12786L389,286@13376L21,281@13189L404:Carousel.kt#dcf9yb");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(687111200, i1112, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:267)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composer2, -685906675, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue2 = composer2.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = new CarouselItemInfoImpl();
                                composer2.updateRememberedValue(objRememberedValue2);
                            }
                            final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685904727, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue3 = composer2.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                composer2.updateRememberedValue(objRememberedValue3);
                            }
                            CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685901457, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue4 = composer2.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$clipShape$1$1
                                    @Override // androidx.compose.ui.graphics.Shape
                                    /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                    public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                        return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue4);
                            }
                            CarouselKt$Carousel$1$clipShape$1$1 carouselKt$Carousel$1$clipShape$1$1 = (CarouselKt$Carousel$1$clipShape$1$1) objRememberedValue4;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier.Companion companion = Modifier.INSTANCE;
                            CarouselState carouselState3 = carouselState2;
                            ComposerKt.sourceInformationMarkerStart(composer2, -685882945, "CC(remember):Carousel.kt#9igjgp");
                            boolean zChanged = composer2.changed(carouselPageSize);
                            final CarouselPageSize carouselPageSize2 = carouselPageSize;
                            Object objRememberedValue5 = composer2.rememberedValue();
                            if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$1$1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Strategy invoke() {
                                        return carouselPageSize2.getStrategy();
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue5);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i1111, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$1$clipShape$1$1);
                            Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                            ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1112607687, "C291@13566L13:Carousel.kt#dcf9yb");
                            function5.invoke(carouselItemScopeImpl, Integer.valueOf(i1111), composer2, Integer.valueOf(i1112 & 112));
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    modifier4 = modifier2;
                    if (orientation == Orientation.Vertical) {
                        composerStartRestartGroup.startReplaceGroup(-1616959128);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "300@13962L7,301@14054L7,309@14347L1014,295@13666L1695");
                        PagerState pagerState5 = carouselState2.getPagerState();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection9 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume9 = composerStartRestartGroup.consume(localLayoutDirection9);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        float fCalculateStartPadding5 = PaddingKt.calculateStartPadding(paddingValues, (LayoutDirection) objConsume9);
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection10 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume10 = composerStartRestartGroup.consume(localLayoutDirection10);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        PagerKt.m965VerticalPageroI3XNZo(pagerState5, modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(fCalculateStartPadding5, 0.0f, PaddingKt.calculateEndPadding(paddingValues, (LayoutDirection) objConsume10), 0.0f, 10, null), carouselPageSize, i, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(-817308503, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                                invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(PagerScope pagerScope, int i1111, Composer composer2, int i1112) {
                                ComposerKt.sourceInformation(composer2, "C310@14392L35,311@14452L63,312@14544L389,329@15134L21,324@14947L404:Carousel.kt#dcf9yb");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-817308503, i1112, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:310)");
                                }
                                ComposerKt.sourceInformationMarkerStart(composer2, -685850419, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue2 = composer2.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = new CarouselItemInfoImpl();
                                    composer2.updateRememberedValue(objRememberedValue2);
                                }
                                final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -685848471, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue3 = composer2.rememberedValue();
                                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                    composer2.updateRememberedValue(objRememberedValue3);
                                }
                                CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -685845201, "CC(remember):Carousel.kt#9igjgp");
                                Object objRememberedValue4 = composer2.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$clipShape$1$1
                                        @Override // androidx.compose.ui.graphics.Shape
                                        /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                        public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                            return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue4);
                                }
                                CarouselKt$Carousel$2$clipShape$1$1 carouselKt$Carousel$2$clipShape$1$1 = (CarouselKt$Carousel$2$clipShape$1$1) objRememberedValue4;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier.Companion companion = Modifier.INSTANCE;
                                CarouselState carouselState3 = carouselState2;
                                ComposerKt.sourceInformationMarkerStart(composer2, -685826689, "CC(remember):Carousel.kt#9igjgp");
                                boolean zChanged = composer2.changed(carouselPageSize);
                                final CarouselPageSize carouselPageSize2 = carouselPageSize;
                                Object objRememberedValue5 = composer2.rememberedValue();
                                if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$1$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Strategy invoke() {
                                            return carouselPageSize2.getStrategy();
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue5);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i1111, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$2$clipShape$1$1);
                                Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                                ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1114351623, "C334@15324L13:Carousel.kt#dcf9yb");
                                function5.invoke(carouselItemScopeImpl, Integer.valueOf(i1111), composer2, Integer.valueOf(i1112 & 112));
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1615314857);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                f3 = f2;
                targetedFlingBehavior3 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier7 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$3
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

                    public final void invoke(Composer composer2, int i1111) {
                        CarouselKt.m2601CarouselV95POc(carouselState2, orientation, function2, paddingValues, i, modifier7, f3, targetedFlingBehavior3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 1572864;
        fM6890constructorimpl = f;
        if ((i2 & 12582912) == 0) {
            if ((i3 & 128) == 0) {
                targetedFlingBehavior2 = targetedFlingBehavior;
                if (composerStartRestartGroup.changed(targetedFlingBehavior2)) {
                }
                i4 |= i11;
            } else {
                targetedFlingBehavior2 = targetedFlingBehavior;
            }
            i4 |= i11;
        } else {
            targetedFlingBehavior2 = targetedFlingBehavior;
        }
        if ((i3 & 256) != 0) {
            i4 |= 100663296;
        } else if ((i2 & 100663296) == 0) {
            if (composerStartRestartGroup.changedInstance(function4)) {
                i8 = 67108864;
            } else {
                i8 = 33554432;
            }
            i4 |= i8;
        }
        i9 = i4;
        if ((38347923 & i9) == 38347922) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i10 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i6 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                if ((i3 & 128) != 0) {
                    CarouselDefaults carouselDefaults10 = CarouselDefaults.INSTANCE;
                    int i1111 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                    carouselState2 = carouselState;
                    i9 &= -29360129;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults10.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1111, 2);
                } else {
                    carouselState2 = carouselState;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                }
                modifier2 = modifier3;
            } else {
                if (i10 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i6 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                if ((i3 & 128) != 0) {
                    CarouselDefaults carouselDefaults11 = CarouselDefaults.INSTANCE;
                    int i1112 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                    carouselState2 = carouselState;
                    i9 &= -29360129;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults11.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1112, 2);
                } else {
                    carouselState2 = carouselState;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                }
                modifier2 = modifier3;
            }
            f2 = fM6890constructorimpl;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2035733443, i9, -1, "androidx.compose.material3.carousel.Carousel (Carousel.kt:241)");
            }
            int i1113 = ((i9 >> 9) & 14) | (i9 & 112);
            fCalculateBeforeContentPadding = calculateBeforeContentPadding(paddingValues, orientation, composerStartRestartGroup, i1113);
            fCalculateAfterContentPadding = calculateAfterContentPadding(paddingValues, orientation, composerStartRestartGroup, i1113);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2025986753, "CC(remember):Carousel.kt#9igjgp");
            if ((i9 & 896) == 256) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            carouselPageSize = (CarouselPageSize) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            snapPositionKeylineSnapPosition = KeylineSnapPositionKt.KeylineSnapPosition(carouselPageSize);
            if (orientation == Orientation.Horizontal) {
                composerStartRestartGroup.startReplaceGroup(-1618653092);
                ComposerKt.sourceInformation(composerStartRestartGroup, "266@12589L1014,252@11960L1643");
                int i1114 = i5;
                modifier4 = modifier2;
                PagerKt.m964HorizontalPageroI3XNZo(carouselState2.getPagerState(), modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(0.0f, paddingValues.getTop(), 0.0f, paddingValues.getBottom(), 5, null), carouselPageSize, i1114, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(687111200, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                        invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(PagerScope pagerScope, int i1115, Composer composer2, int i1116) {
                        ComposerKt.sourceInformation(composer2, "C267@12634L35,268@12694L63,269@12786L389,286@13376L21,281@13189L404:Carousel.kt#dcf9yb");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(687111200, i1116, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:267)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composer2, -685906675, "CC(remember):Carousel.kt#9igjgp");
                        Object objRememberedValue2 = composer2.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = new CarouselItemInfoImpl();
                            composer2.updateRememberedValue(objRememberedValue2);
                        }
                        final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerStart(composer2, -685904727, "CC(remember):Carousel.kt#9igjgp");
                        Object objRememberedValue3 = composer2.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                            composer2.updateRememberedValue(objRememberedValue3);
                        }
                        CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerStart(composer2, -685901457, "CC(remember):Carousel.kt#9igjgp");
                        Object objRememberedValue4 = composer2.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$clipShape$1$1
                                @Override // androidx.compose.ui.graphics.Shape
                                /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                    return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue4);
                        }
                        CarouselKt$Carousel$1$clipShape$1$1 carouselKt$Carousel$1$clipShape$1$1 = (CarouselKt$Carousel$1$clipShape$1$1) objRememberedValue4;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        Modifier.Companion companion = Modifier.INSTANCE;
                        CarouselState carouselState3 = carouselState2;
                        ComposerKt.sourceInformationMarkerStart(composer2, -685882945, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged = composer2.changed(carouselPageSize);
                        final CarouselPageSize carouselPageSize2 = carouselPageSize;
                        Object objRememberedValue5 = composer2.rememberedValue();
                        if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$1$1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Strategy invoke() {
                                    return carouselPageSize2.getStrategy();
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue5);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i1115, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$1$clipShape$1$1);
                        Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                        ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer2, 1112607687, "C291@13566L13:Carousel.kt#dcf9yb");
                        function5.invoke(carouselItemScopeImpl, Integer.valueOf(i1115), composer2, Integer.valueOf(i1116 & 112));
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                modifier4 = modifier2;
                if (orientation == Orientation.Vertical) {
                    composerStartRestartGroup.startReplaceGroup(-1616959128);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "300@13962L7,301@14054L7,309@14347L1014,295@13666L1695");
                    PagerState pagerState6 = carouselState2.getPagerState();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11 = composerStartRestartGroup.consume(localLayoutDirection11);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    float fCalculateStartPadding6 = PaddingKt.calculateStartPadding(paddingValues, (LayoutDirection) objConsume11);
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection12 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume12 = composerStartRestartGroup.consume(localLayoutDirection12);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    PagerKt.m965VerticalPageroI3XNZo(pagerState6, modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(fCalculateStartPadding6, 0.0f, PaddingKt.calculateEndPadding(paddingValues, (LayoutDirection) objConsume12), 0.0f, 10, null), carouselPageSize, i, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(-817308503, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                            invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PagerScope pagerScope, int i1115, Composer composer2, int i1116) {
                            ComposerKt.sourceInformation(composer2, "C310@14392L35,311@14452L63,312@14544L389,329@15134L21,324@14947L404:Carousel.kt#dcf9yb");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-817308503, i1116, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:310)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composer2, -685850419, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue2 = composer2.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = new CarouselItemInfoImpl();
                                composer2.updateRememberedValue(objRememberedValue2);
                            }
                            final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685848471, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue3 = composer2.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                composer2.updateRememberedValue(objRememberedValue3);
                            }
                            CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685845201, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue4 = composer2.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$clipShape$1$1
                                    @Override // androidx.compose.ui.graphics.Shape
                                    /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                    public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                        return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue4);
                            }
                            CarouselKt$Carousel$2$clipShape$1$1 carouselKt$Carousel$2$clipShape$1$1 = (CarouselKt$Carousel$2$clipShape$1$1) objRememberedValue4;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier.Companion companion = Modifier.INSTANCE;
                            CarouselState carouselState3 = carouselState2;
                            ComposerKt.sourceInformationMarkerStart(composer2, -685826689, "CC(remember):Carousel.kt#9igjgp");
                            boolean zChanged = composer2.changed(carouselPageSize);
                            final CarouselPageSize carouselPageSize2 = carouselPageSize;
                            Object objRememberedValue5 = composer2.rememberedValue();
                            if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$1$1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Strategy invoke() {
                                        return carouselPageSize2.getStrategy();
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue5);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i1115, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$2$clipShape$1$1);
                            Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                            ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1114351623, "C334@15324L13:Carousel.kt#dcf9yb");
                            function5.invoke(carouselItemScopeImpl, Integer.valueOf(i1115), composer2, Integer.valueOf(i1116 & 112));
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1615314857);
                    composerStartRestartGroup.endReplaceGroup();
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            f3 = f2;
            targetedFlingBehavior3 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i10 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i6 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                if ((i3 & 128) != 0) {
                    CarouselDefaults carouselDefaults12 = CarouselDefaults.INSTANCE;
                    int i1115 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                    carouselState2 = carouselState;
                    i9 &= -29360129;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults12.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1115, 2);
                } else {
                    carouselState2 = carouselState;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                }
                modifier2 = modifier3;
            } else {
                if (i10 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i6 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                if ((i3 & 128) != 0) {
                    CarouselDefaults carouselDefaults13 = CarouselDefaults.INSTANCE;
                    int i1116 = (i9 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                    carouselState2 = carouselState;
                    i9 &= -29360129;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = carouselDefaults13.singleAdvanceFlingBehavior(carouselState2, null, composerStartRestartGroup, i1116, 2);
                } else {
                    carouselState2 = carouselState;
                    targetedFlingBehaviorSingleAdvanceFlingBehavior = targetedFlingBehavior2;
                }
                modifier2 = modifier3;
            }
            f2 = fM6890constructorimpl;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2035733443, i9, -1, "androidx.compose.material3.carousel.Carousel (Carousel.kt:241)");
            }
            int i1117 = ((i9 >> 9) & 14) | (i9 & 112);
            fCalculateBeforeContentPadding = calculateBeforeContentPadding(paddingValues, orientation, composerStartRestartGroup, i1117);
            fCalculateAfterContentPadding = calculateAfterContentPadding(paddingValues, orientation, composerStartRestartGroup, i1117);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2025986753, "CC(remember):Carousel.kt#9igjgp");
            if ((i9 & 896) == 256) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new CarouselPageSize(function2, fCalculateBeforeContentPadding, fCalculateAfterContentPadding);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            carouselPageSize = (CarouselPageSize) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            snapPositionKeylineSnapPosition = KeylineSnapPositionKt.KeylineSnapPosition(carouselPageSize);
            if (orientation == Orientation.Horizontal) {
                composerStartRestartGroup.startReplaceGroup(-1618653092);
                ComposerKt.sourceInformation(composerStartRestartGroup, "266@12589L1014,252@11960L1643");
                int i1118 = i5;
                modifier4 = modifier2;
                PagerKt.m964HorizontalPageroI3XNZo(carouselState2.getPagerState(), modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(0.0f, paddingValues.getTop(), 0.0f, paddingValues.getBottom(), 5, null), carouselPageSize, i1118, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(687111200, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                        invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(PagerScope pagerScope, int i1119, Composer composer2, int i11110) {
                        ComposerKt.sourceInformation(composer2, "C267@12634L35,268@12694L63,269@12786L389,286@13376L21,281@13189L404:Carousel.kt#dcf9yb");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(687111200, i11110, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:267)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composer2, -685906675, "CC(remember):Carousel.kt#9igjgp");
                        Object objRememberedValue2 = composer2.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = new CarouselItemInfoImpl();
                            composer2.updateRememberedValue(objRememberedValue2);
                        }
                        final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerStart(composer2, -685904727, "CC(remember):Carousel.kt#9igjgp");
                        Object objRememberedValue3 = composer2.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                            composer2.updateRememberedValue(objRememberedValue3);
                        }
                        CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerStart(composer2, -685901457, "CC(remember):Carousel.kt#9igjgp");
                        Object objRememberedValue4 = composer2.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$clipShape$1$1
                                @Override // androidx.compose.ui.graphics.Shape
                                /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                    return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue4);
                        }
                        CarouselKt$Carousel$1$clipShape$1$1 carouselKt$Carousel$1$clipShape$1$1 = (CarouselKt$Carousel$1$clipShape$1$1) objRememberedValue4;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        Modifier.Companion companion = Modifier.INSTANCE;
                        CarouselState carouselState3 = carouselState2;
                        ComposerKt.sourceInformationMarkerStart(composer2, -685882945, "CC(remember):Carousel.kt#9igjgp");
                        boolean zChanged = composer2.changed(carouselPageSize);
                        final CarouselPageSize carouselPageSize2 = carouselPageSize;
                        Object objRememberedValue5 = composer2.rememberedValue();
                        if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$1$1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Strategy invoke() {
                                    return carouselPageSize2.getStrategy();
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue5);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i1119, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$1$clipShape$1$1);
                        Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                        ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer2, 1112607687, "C291@13566L13:Carousel.kt#dcf9yb");
                        function5.invoke(carouselItemScopeImpl, Integer.valueOf(i1119), composer2, Integer.valueOf(i11110 & 112));
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                modifier4 = modifier2;
                if (orientation == Orientation.Vertical) {
                    composerStartRestartGroup.startReplaceGroup(-1616959128);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "300@13962L7,301@14054L7,309@14347L1014,295@13666L1695");
                    PagerState pagerState7 = carouselState2.getPagerState();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection13 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume13 = composerStartRestartGroup.consume(localLayoutDirection13);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    float fCalculateStartPadding7 = PaddingKt.calculateStartPadding(paddingValues, (LayoutDirection) objConsume13);
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection14 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume14 = composerStartRestartGroup.consume(localLayoutDirection14);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    PagerKt.m965VerticalPageroI3XNZo(pagerState7, modifier4, PaddingKt.m717PaddingValuesa9UjIt4$default(fCalculateStartPadding7, 0.0f, PaddingKt.calculateEndPadding(paddingValues, (LayoutDirection) objConsume14), 0.0f, 10, null), carouselPageSize, i, f2, null, targetedFlingBehaviorSingleAdvanceFlingBehavior, false, false, null, null, snapPositionKeylineSnapPosition, ComposableLambdaKt.rememberComposableLambda(-817308503, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer2, Integer num2) {
                            invoke(pagerScope, num.intValue(), composer2, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PagerScope pagerScope, int i1119, Composer composer2, int i11110) {
                            ComposerKt.sourceInformation(composer2, "C310@14392L35,311@14452L63,312@14544L389,329@15134L21,324@14947L404:Carousel.kt#dcf9yb");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-817308503, i11110, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:310)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composer2, -685850419, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue2 = composer2.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = new CarouselItemInfoImpl();
                                composer2.updateRememberedValue(objRememberedValue2);
                            }
                            final CarouselItemInfoImpl carouselItemInfoImpl = (CarouselItemInfoImpl) objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685848471, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue3 = composer2.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new CarouselItemScopeImpl(carouselItemInfoImpl);
                                composer2.updateRememberedValue(objRememberedValue3);
                            }
                            CarouselItemScopeImpl carouselItemScopeImpl = (CarouselItemScopeImpl) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -685845201, "CC(remember):Carousel.kt#9igjgp");
                            Object objRememberedValue4 = composer2.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$clipShape$1$1
                                    @Override // androidx.compose.ui.graphics.Shape
                                    /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                    public Outline mo317createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                        return new Outline.Rectangle(carouselItemInfoImpl.getMaskRect());
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue4);
                            }
                            CarouselKt$Carousel$2$clipShape$1$1 carouselKt$Carousel$2$clipShape$1$1 = (CarouselKt$Carousel$2$clipShape$1$1) objRememberedValue4;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier.Companion companion = Modifier.INSTANCE;
                            CarouselState carouselState3 = carouselState2;
                            ComposerKt.sourceInformationMarkerStart(composer2, -685826689, "CC(remember):Carousel.kt#9igjgp");
                            boolean zChanged = composer2.changed(carouselPageSize);
                            final CarouselPageSize carouselPageSize2 = carouselPageSize;
                            Object objRememberedValue5 = composer2.rememberedValue();
                            if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue5 = (Function0) new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$1$1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Strategy invoke() {
                                        return carouselPageSize2.getStrategy();
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue5);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierCarouselItem = CarouselKt.carouselItem(companion, i1119, carouselState3, (Function0) objRememberedValue5, carouselItemInfoImpl, carouselKt$Carousel$2$clipShape$1$1);
                            Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                            ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierCarouselItem);
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
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1114351623, "C334@15324L13:Carousel.kt#dcf9yb");
                            function5.invoke(carouselItemScopeImpl, Integer.valueOf(i1119), composer2, Integer.valueOf(i11110 & 112));
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i9 >> 12) & 112) | (i9 & 57344) | ((i9 >> 3) & 458752) | (29360128 & i9), 3072, 3904);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1615314857);
                    composerStartRestartGroup.endReplaceGroup();
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            f3 = f2;
            targetedFlingBehavior3 = targetedFlingBehaviorSingleAdvanceFlingBehavior;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier8 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$3
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

                public final void invoke(Composer composer2, int i1119) {
                    CarouselKt.m2601CarouselV95POc(carouselState2, orientation, function2, paddingValues, i, modifier8, f3, targetedFlingBehavior3, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    private static final float calculateBeforeContentPadding(PaddingValues paddingValues, Orientation orientation, Composer composer, int i) {
        float fCalculateStartPadding;
        ComposerKt.sourceInformationMarkerStart(composer, 1896839347, "C(calculateBeforeContentPadding)*349@15698L7:Carousel.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1896839347, i, -1, "androidx.compose.material3.carousel.calculateBeforeContentPadding (Carousel.kt:341)");
        }
        composer.startReplaceGroup(295830617);
        ComposerKt.sourceInformation(composer, "346@15649L7");
        if (orientation == Orientation.Vertical) {
            fCalculateStartPadding = paddingValues.getTop();
        } else {
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composer);
            fCalculateStartPadding = PaddingKt.calculateStartPadding(paddingValues, (LayoutDirection) objConsume);
        }
        composer.endReplaceGroup();
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composer);
        float fMo406toPx0680j_4 = ((Density) objConsume2).mo406toPx0680j_4(fCalculateStartPadding);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return fMo406toPx0680j_4;
    }

    private static final float calculateAfterContentPadding(PaddingValues paddingValues, Orientation orientation, Composer composer, int i) {
        float fCalculateEndPadding;
        ComposerKt.sourceInformationMarkerStart(composer, 1018496720, "C(calculateAfterContentPadding)*361@16056L7:Carousel.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1018496720, i, -1, "androidx.compose.material3.carousel.calculateAfterContentPadding (Carousel.kt:353)");
        }
        composer.startReplaceGroup(-587616383);
        ComposerKt.sourceInformation(composer, "358@16007L7");
        if (orientation == Orientation.Vertical) {
            fCalculateEndPadding = paddingValues.getBottom();
        } else {
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composer);
            fCalculateEndPadding = PaddingKt.calculateEndPadding(paddingValues, (LayoutDirection) objConsume);
        }
        composer.endReplaceGroup();
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composer);
        float fMo406toPx0680j_4 = ((Density) objConsume2).mo406toPx0680j_4(fCalculateEndPadding);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return fMo406toPx0680j_4;
    }

    public static final Modifier carouselItem(Modifier modifier, final int i, final CarouselState carouselState, final Function0<Strategy> function0, final CarouselItemInfoImpl carouselItemInfoImpl, final Shape shape) {
        return LayoutModifierKt.layout(modifier, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.carousel.CarouselKt.carouselItem.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                return m2604invoke3p2s80s(measureScope, measurable, constraints.getValue());
            }

            /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m2604invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                long jM6822copyZbe2FdA;
                final Strategy strategyInvoke = function0.invoke();
                if (!strategyInvoke.getIsValid()) {
                    return MeasureScope.CC.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt.carouselItem.1.1
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
                final boolean z = carouselState.getPagerState().getLayoutInfo().getOrientation() == Orientation.Vertical;
                final boolean z2 = measureScope.getLayoutDirection() == LayoutDirection.Rtl;
                float itemMainAxisSize = strategyInvoke.getItemMainAxisSize();
                if (z) {
                    jM6822copyZbe2FdA = Constraints.m6822copyZbe2FdA(j, Constraints.m6835getMinWidthimpl(j), Constraints.m6833getMaxWidthimpl(j), MathKt.roundToInt(itemMainAxisSize), MathKt.roundToInt(itemMainAxisSize));
                } else {
                    jM6822copyZbe2FdA = Constraints.m6822copyZbe2FdA(j, MathKt.roundToInt(itemMainAxisSize), MathKt.roundToInt(itemMainAxisSize), Constraints.m6834getMinHeightimpl(j), Constraints.m6832getMaxHeightimpl(j));
                }
                final Placeable placeableMo5581measureBRTryo0 = measurable.mo5581measureBRTryo0(jM6822copyZbe2FdA);
                int width = placeableMo5581measureBRTryo0.getWidth();
                int height = placeableMo5581measureBRTryo0.getHeight();
                final CarouselState carouselState2 = carouselState;
                final int i2 = i;
                final CarouselItemInfoImpl carouselItemInfoImpl2 = carouselItemInfoImpl;
                final Shape shape2 = shape;
                return MeasureScope.CC.layout$default(measureScope, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt.carouselItem.1.2
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
                        Placeable placeable = placeableMo5581measureBRTryo0;
                        final CarouselState carouselState3 = carouselState2;
                        final Strategy strategy = strategyInvoke;
                        final int i3 = i2;
                        final boolean z3 = z;
                        final CarouselItemInfoImpl carouselItemInfoImpl3 = carouselItemInfoImpl2;
                        final Shape shape3 = shape2;
                        final boolean z4 = z2;
                        Placeable.PlacementScope.placeWithLayer$default(placementScope, placeable, 0, 0, 0.0f, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt.carouselItem.1.2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                invoke2(graphicsLayerScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                float fCalculateCurrentScrollOffset = CarouselKt.calculateCurrentScrollOffset(carouselState3, strategy);
                                float fCalculateMaxScrollOffset = CarouselKt.calculateMaxScrollOffset(carouselState3, strategy);
                                KeylineList keylineListForScrollOffset$material3_release$default = Strategy.getKeylineListForScrollOffset$material3_release$default(strategy, fCalculateCurrentScrollOffset, fCalculateMaxScrollOffset, false, 4, null);
                                KeylineList keylineListForScrollOffset$material3_release = strategy.getKeylineListForScrollOffset$material3_release(fCalculateCurrentScrollOffset, fCalculateMaxScrollOffset, true);
                                float itemMainAxisSize2 = ((i3 * (strategy.getItemMainAxisSize() + strategy.getItemSpacing())) + (strategy.getItemMainAxisSize() / 2.0f)) - fCalculateCurrentScrollOffset;
                                Keyline keylineBefore = keylineListForScrollOffset$material3_release$default.getKeylineBefore(itemMainAxisSize2);
                                Keyline keylineAfter = keylineListForScrollOffset$material3_release$default.getKeylineAfter(itemMainAxisSize2);
                                Keyline keylineLerp = KeylineListKt.lerp(keylineBefore, keylineAfter, CarouselKt.getProgress(keylineBefore, keylineAfter, itemMainAxisSize2));
                                boolean zAreEqual = Intrinsics.areEqual(keylineBefore, keylineAfter);
                                float fM3936getHeightimpl = (z3 ? Size.m3936getHeightimpl(graphicsLayerScope.getSize()) : strategy.getItemMainAxisSize()) / 2.0f;
                                float itemMainAxisSize3 = (z3 ? strategy.getItemMainAxisSize() : Size.m3936getHeightimpl(graphicsLayerScope.getSize())) / 2.0f;
                                float fM3939getWidthimpl = (z3 ? Size.m3939getWidthimpl(graphicsLayerScope.getSize()) : keylineLerp.getSize()) / 2.0f;
                                float size = (z3 ? keylineLerp.getSize() : Size.m3936getHeightimpl(graphicsLayerScope.getSize())) / 2.0f;
                                Rect rect = new Rect(fM3936getHeightimpl - fM3939getWidthimpl, itemMainAxisSize3 - size, fM3936getHeightimpl + fM3939getWidthimpl, itemMainAxisSize3 + size);
                                carouselItemInfoImpl3.setSizeState(keylineLerp.getSize());
                                CarouselItemInfoImpl carouselItemInfoImpl4 = carouselItemInfoImpl3;
                                Iterator<Keyline> it = keylineListForScrollOffset$material3_release.iterator();
                                if (!it.hasNext()) {
                                    throw new NoSuchElementException();
                                }
                                Keyline next = it.next();
                                if (it.hasNext()) {
                                    float size2 = next.getSize();
                                    do {
                                        Keyline next2 = it.next();
                                        float size3 = next2.getSize();
                                        if (Float.compare(size2, size3) > 0) {
                                            next = next2;
                                            size2 = size3;
                                        }
                                    } while (it.hasNext());
                                }
                                carouselItemInfoImpl4.setMinSizeState(next.getSize());
                                carouselItemInfoImpl3.setMaxSizeState(keylineListForScrollOffset$material3_release.getFirstFocal().getSize());
                                carouselItemInfoImpl3.setMaskRectState(rect);
                                graphicsLayerScope.setClip(!Intrinsics.areEqual(rect, new Rect(0.0f, 0.0f, Size.m3939getWidthimpl(graphicsLayerScope.getSize()), Size.m3936getHeightimpl(graphicsLayerScope.getSize()))));
                                graphicsLayerScope.setShape(shape3);
                                float offset = keylineLerp.getOffset() - itemMainAxisSize2;
                                if (zAreEqual) {
                                    offset += (itemMainAxisSize2 - keylineLerp.getUnadjustedOffset()) / keylineLerp.getSize();
                                }
                                if (z3) {
                                    graphicsLayerScope.setTranslationY(offset);
                                    return;
                                }
                                if (z4) {
                                    offset = -offset;
                                }
                                graphicsLayerScope.setTranslationX(offset);
                            }
                        }, 4, (Object) null);
                    }
                }, 4, null);
            }
        });
    }

    public static final float calculateCurrentScrollOffset(CarouselState carouselState, Strategy strategy) {
        float itemMainAxisSize = strategy.getItemMainAxisSize() + strategy.getItemSpacing();
        return ((carouselState.getPagerState().getCurrentPage() * itemMainAxisSize) + (carouselState.getPagerState().getCurrentPageOffsetFraction() * itemMainAxisSize)) - KeylineSnapPositionKt.getSnapPositionOffset(strategy, carouselState.getPagerState().getCurrentPage(), carouselState.getPagerState().getPageCount());
    }

    public static final float calculateMaxScrollOffset(CarouselState carouselState, Strategy strategy) {
        float pageCount = carouselState.getPagerState().getPageCount();
        return RangesKt.coerceAtLeast(((strategy.getItemMainAxisSize() * pageCount) + (strategy.getItemSpacing() * (pageCount - 1.0f))) - strategy.getAvailableSpace(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float getProgress(Keyline keyline, Keyline keyline2, float f) {
        if (Intrinsics.areEqual(keyline, keyline2)) {
            return 1.0f;
        }
        return (f - keyline.getUnadjustedOffset()) / (keyline2.getUnadjustedOffset() - keyline.getUnadjustedOffset());
    }
}
