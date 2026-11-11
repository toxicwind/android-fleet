package androidx.compose.animation;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Crossfade.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u001aN\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\f\u001aX\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\u000f\u001a\u0086\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00102\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072%\b\u0002\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00140\n2&\u0010\t\u001a\"\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\u0015¨\u0006\u0016²\u0006\u0010\u0010\u0017\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002X\u008a\u0084\u0002"}, d2 = {"Crossfade", "", ExifInterface.GPS_DIRECTION_TRUE, "targetState", "modifier", "Landroidx/compose/ui/Modifier;", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "content", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "label", "", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/FiniteAnimationSpec;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/animation/core/Transition;", "contentKey", "Lkotlin/ParameterName;", "name", "", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "animation_release", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CrossfadeKt {
    /* JADX WARN: Code duplicated, block: B:29:0x0052  */
    /* JADX WARN: Code duplicated, block: B:30:0x0055  */
    /* JADX WARN: Code duplicated, block: B:32:0x0059  */
    /* JADX WARN: Code duplicated, block: B:34:0x0061  */
    /* JADX WARN: Code duplicated, block: B:35:0x0064  */
    /* JADX WARN: Code duplicated, block: B:40:0x006e  */
    /* JADX WARN: Code duplicated, block: B:41:0x0071  */
    /* JADX WARN: Code duplicated, block: B:43:0x0075  */
    /* JADX WARN: Code duplicated, block: B:45:0x007d  */
    /* JADX WARN: Code duplicated, block: B:46:0x0080  */
    /* JADX WARN: Code duplicated, block: B:51:0x008a  */
    /* JADX WARN: Code duplicated, block: B:52:0x008f  */
    /* JADX WARN: Code duplicated, block: B:54:0x0095  */
    /* JADX WARN: Code duplicated, block: B:56:0x009b  */
    /* JADX WARN: Code duplicated, block: B:57:0x009e  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:66:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:67:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:73:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:80:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:84:0x0106  */
    /* JADX WARN: Code duplicated, block: B:86:? A[RETURN, SYNTHETIC] */
    public static final <T> void Crossfade(final T t, Modifier modifier, FiniteAnimationSpec<Float> finiteAnimationSpec, String str, final Function3<? super T, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        FiniteAnimationSpec<Float> finiteAnimationSpec2;
        int i5;
        int i6;
        String str2;
        int i7;
        Function3<? super T, ? super Composer, ? super Integer, Unit> function4;
        int i8;
        Modifier.Companion companion;
        TweenSpec tweenSpecTween$default;
        String str3;
        final Modifier modifier3;
        final FiniteAnimationSpec<Float> finiteAnimationSpec3;
        final String str4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-310686752);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Crossfade)P(4,3!1,2)56@2327L36,57@2379L53:Crossfade.kt#xbi5r1");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(t) : composerStartRestartGroup.changedInstance(t) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i9 = i2 & 2;
        if (i9 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    finiteAnimationSpec2 = finiteAnimationSpec;
                    if (composerStartRestartGroup.changedInstance(finiteAnimationSpec2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        str2 = str;
                        if (composerStartRestartGroup.changed(str2)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i2 & 16) != 0) {
                        i3 |= 24576;
                        function4 = function3;
                    } else {
                        function4 = function3;
                        if ((i & 24576) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i8 = 16384;
                            } else {
                                i8 = 8192;
                            }
                            i3 |= i8;
                        }
                    }
                    if ((i3 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                        } else {
                            tweenSpecTween$default = finiteAnimationSpec2;
                        }
                        if (i6 != 0) {
                            str3 = "Crossfade";
                        } else {
                            str3 = str2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                        }
                        Modifier modifier4 = companion;
                        FiniteAnimationSpec<Float> finiteAnimationSpec4 = tweenSpecTween$default;
                        Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier4, finiteAnimationSpec4, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        finiteAnimationSpec3 = finiteAnimationSpec4;
                        str4 = str3;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        finiteAnimationSpec3 = finiteAnimationSpec2;
                        str4 = str2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
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

                            public final void invoke(Composer composer2, int i10) {
                                CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 3072;
                str2 = str;
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                    function4 = function3;
                } else {
                    function4 = function3;
                    if ((i & 24576) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                }
                if ((i3 & 9363) == 9362) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        str3 = "Crossfade";
                    } else {
                        str3 = str2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                    }
                    Modifier modifier5 = companion;
                    FiniteAnimationSpec<Float> finiteAnimationSpec5 = tweenSpecTween$default;
                    Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier5, finiteAnimationSpec5, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    finiteAnimationSpec3 = finiteAnimationSpec5;
                    str4 = str3;
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        str3 = "Crossfade";
                    } else {
                        str3 = str2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                    }
                    Modifier modifier6 = companion;
                    FiniteAnimationSpec<Float> finiteAnimationSpec6 = tweenSpecTween$default;
                    Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier6, finiteAnimationSpec6, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier6;
                    finiteAnimationSpec3 = finiteAnimationSpec6;
                    str4 = str3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
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

                        public final void invoke(Composer composer2, int i10) {
                            CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            finiteAnimationSpec2 = finiteAnimationSpec;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    str2 = str;
                    if (composerStartRestartGroup.changed(str2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                    function4 = function3;
                } else {
                    function4 = function3;
                    if ((i & 24576) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                }
                if ((i3 & 9363) == 9362) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        str3 = "Crossfade";
                    } else {
                        str3 = str2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                    }
                    Modifier modifier7 = companion;
                    FiniteAnimationSpec<Float> finiteAnimationSpec7 = tweenSpecTween$default;
                    Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier7, finiteAnimationSpec7, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier7;
                    finiteAnimationSpec3 = finiteAnimationSpec7;
                    str4 = str3;
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        str3 = "Crossfade";
                    } else {
                        str3 = str2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                    }
                    Modifier modifier8 = companion;
                    FiniteAnimationSpec<Float> finiteAnimationSpec8 = tweenSpecTween$default;
                    Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier8, finiteAnimationSpec8, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier8;
                    finiteAnimationSpec3 = finiteAnimationSpec8;
                    str4 = str3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
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

                        public final void invoke(Composer composer2, int i10) {
                            CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            str2 = str;
            if ((i2 & 16) != 0) {
                i3 |= 24576;
                function4 = function3;
            } else {
                function4 = function3;
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
            }
            if ((i3 & 9363) == 9362) {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    str3 = "Crossfade";
                } else {
                    str3 = str2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                }
                Modifier modifier9 = companion;
                FiniteAnimationSpec<Float> finiteAnimationSpec9 = tweenSpecTween$default;
                Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier9, finiteAnimationSpec9, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier9;
                finiteAnimationSpec3 = finiteAnimationSpec9;
                str4 = str3;
            } else {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    str3 = "Crossfade";
                } else {
                    str3 = str2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                }
                Modifier modifier10 = companion;
                FiniteAnimationSpec<Float> finiteAnimationSpec10 = tweenSpecTween$default;
                Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier10, finiteAnimationSpec10, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier10;
                finiteAnimationSpec3 = finiteAnimationSpec10;
                str4 = str3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
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

                    public final void invoke(Composer composer2, int i10) {
                        CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                finiteAnimationSpec2 = finiteAnimationSpec;
                if (composerStartRestartGroup.changedInstance(finiteAnimationSpec2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    str2 = str;
                    if (composerStartRestartGroup.changed(str2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                    function4 = function3;
                } else {
                    function4 = function3;
                    if ((i & 24576) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                }
                if ((i3 & 9363) == 9362) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        str3 = "Crossfade";
                    } else {
                        str3 = str2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                    }
                    Modifier modifier11 = companion;
                    FiniteAnimationSpec<Float> finiteAnimationSpec11 = tweenSpecTween$default;
                    Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier11, finiteAnimationSpec11, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier11;
                    finiteAnimationSpec3 = finiteAnimationSpec11;
                    str4 = str3;
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        str3 = "Crossfade";
                    } else {
                        str3 = str2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                    }
                    Modifier modifier12 = companion;
                    FiniteAnimationSpec<Float> finiteAnimationSpec12 = tweenSpecTween$default;
                    Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier12, finiteAnimationSpec12, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier12;
                    finiteAnimationSpec3 = finiteAnimationSpec12;
                    str4 = str3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
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

                        public final void invoke(Composer composer2, int i10) {
                            CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            str2 = str;
            if ((i2 & 16) != 0) {
                i3 |= 24576;
                function4 = function3;
            } else {
                function4 = function3;
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
            }
            if ((i3 & 9363) == 9362) {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    str3 = "Crossfade";
                } else {
                    str3 = str2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                }
                Modifier modifier13 = companion;
                FiniteAnimationSpec<Float> finiteAnimationSpec13 = tweenSpecTween$default;
                Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier13, finiteAnimationSpec13, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier13;
                finiteAnimationSpec3 = finiteAnimationSpec13;
                str4 = str3;
            } else {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    str3 = "Crossfade";
                } else {
                    str3 = str2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                }
                Modifier modifier14 = companion;
                FiniteAnimationSpec<Float> finiteAnimationSpec14 = tweenSpecTween$default;
                Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier14, finiteAnimationSpec14, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier14;
                finiteAnimationSpec3 = finiteAnimationSpec14;
                str4 = str3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
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

                    public final void invoke(Composer composer2, int i10) {
                        CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        finiteAnimationSpec2 = finiteAnimationSpec;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                str2 = str;
                if (composerStartRestartGroup.changed(str2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i2 & 16) != 0) {
                i3 |= 24576;
                function4 = function3;
            } else {
                function4 = function3;
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
            }
            if ((i3 & 9363) == 9362) {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    str3 = "Crossfade";
                } else {
                    str3 = str2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                }
                Modifier modifier15 = companion;
                FiniteAnimationSpec<Float> finiteAnimationSpec15 = tweenSpecTween$default;
                Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier15, finiteAnimationSpec15, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier15;
                finiteAnimationSpec3 = finiteAnimationSpec15;
                str4 = str3;
            } else {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    str3 = "Crossfade";
                } else {
                    str3 = str2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                }
                Modifier modifier16 = companion;
                FiniteAnimationSpec<Float> finiteAnimationSpec16 = tweenSpecTween$default;
                Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier16, finiteAnimationSpec16, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier16;
                finiteAnimationSpec3 = finiteAnimationSpec16;
                str4 = str3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
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

                    public final void invoke(Composer composer2, int i10) {
                        CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        str2 = str;
        if ((i2 & 16) != 0) {
            i3 |= 24576;
            function4 = function3;
        } else {
            function4 = function3;
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
        }
        if ((i3 & 9363) == 9362) {
            if (i9 != 0) {
                companion = Modifier.INSTANCE;
            } else {
                companion = modifier2;
            }
            if (i4 != 0) {
                tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            } else {
                tweenSpecTween$default = finiteAnimationSpec2;
            }
            if (i6 != 0) {
                str3 = "Crossfade";
            } else {
                str3 = str2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
            }
            Modifier modifier17 = companion;
            FiniteAnimationSpec<Float> finiteAnimationSpec17 = tweenSpecTween$default;
            Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier17, finiteAnimationSpec17, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier17;
            finiteAnimationSpec3 = finiteAnimationSpec17;
            str4 = str3;
        } else {
            if (i9 != 0) {
                companion = Modifier.INSTANCE;
            } else {
                companion = modifier2;
            }
            if (i4 != 0) {
                tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            } else {
                tweenSpecTween$default = finiteAnimationSpec2;
            }
            if (i6 != 0) {
                str3 = "Crossfade";
            } else {
                str3 = str2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-310686752, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
            }
            Modifier modifier18 = companion;
            FiniteAnimationSpec<Float> finiteAnimationSpec18 = tweenSpecTween$default;
            Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier18, finiteAnimationSpec18, (Function1) null, function4, composerStartRestartGroup, i3 & 58352, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier18;
            finiteAnimationSpec3 = finiteAnimationSpec18;
            str4 = str3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
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

                public final void invoke(Composer composer2, int i10) {
                    CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Crossfade API now has a new label parameter added.")
    public static final /* synthetic */ void Crossfade(final Object obj, Modifier modifier, FiniteAnimationSpec finiteAnimationSpec, Function3 function3, Composer composer, final int i, final int i2) {
        int i3;
        final Function3 function4;
        final Modifier modifier2;
        final FiniteAnimationSpec finiteAnimationSpec2;
        Composer composerStartRestartGroup = composer.startRestartGroup(523603005);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Crossfade)P(3,2)72@2790L29,73@2835L53:Crossfade.kt#xbi5r1");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(obj) : composerStartRestartGroup.changedInstance(obj) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        int i5 = i2 & 4;
        if (i5 != 0) {
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(finiteAnimationSpec) ? 256 : 128;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 2048 : 1024;
        }
        if ((i3 & 1171) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (i4 != 0) {
                modifier = Modifier.INSTANCE;
            }
            Modifier modifier3 = modifier;
            if (i5 != 0) {
                finiteAnimationSpec = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            }
            FiniteAnimationSpec finiteAnimationSpec3 = finiteAnimationSpec;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(523603005, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:71)");
            }
            Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(obj, (String) null, composerStartRestartGroup, i3 & 14, 2), modifier3, (FiniteAnimationSpec<Float>) finiteAnimationSpec3, (Function1) null, function3, composerStartRestartGroup, (i3 & 1008) | ((i3 << 3) & 57344), 4);
            function4 = function3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            finiteAnimationSpec2 = finiteAnimationSpec3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            function4 = function3;
            finiteAnimationSpec2 = finiteAnimationSpec;
            modifier2 = modifier;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.2
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

                public final void invoke(Composer composer2, int i6) {
                    CrossfadeKt.Crossfade(obj, modifier2, finiteAnimationSpec2, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:105:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:108:0x01dc A[LOOP:0: B:103:0x01bf->B:108:0x01dc, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:112:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:113:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:116:0x01fc A[LOOP:1: B:115:0x01fa->B:116:0x01fc, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:118:0x0220  */
    /* JADX WARN: Code duplicated, block: B:121:0x0268  */
    /* JADX WARN: Code duplicated, block: B:124:0x0274  */
    /* JADX WARN: Code duplicated, block: B:125:0x0278  */
    /* JADX WARN: Code duplicated, block: B:128:0x029d  */
    /* JADX WARN: Code duplicated, block: B:130:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:133:0x02ea  */
    /* JADX WARN: Code duplicated, block: B:135:0x0303  */
    /* JADX WARN: Code duplicated, block: B:136:0x030e  */
    /* JADX WARN: Code duplicated, block: B:140:0x0345  */
    /* JADX WARN: Code duplicated, block: B:144:0x0350  */
    /* JADX WARN: Code duplicated, block: B:146:0x01df A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:147:0x01e0 A[EDGE_INSN: B:147:0x01e0->B:110:0x01e0 BREAK  A[LOOP:0: B:103:0x01bf->B:108:0x01dc], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:152:? A[RETURN, SYNTHETIC] */
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
    /* JADX WARN: Code duplicated, block: B:53:0x0093  */
    /* JADX WARN: Code duplicated, block: B:54:0x0096  */
    /* JADX WARN: Code duplicated, block: B:58:0x009f  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ad A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:63:0x00af  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:67:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:73:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:76:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:79:0x010e  */
    /* JADX WARN: Code duplicated, block: B:82:0x012b  */
    /* JADX WARN: Code duplicated, block: B:84:0x013a  */
    /* JADX WARN: Code duplicated, block: B:88:0x0153  */
    /* JADX WARN: Code duplicated, block: B:90:0x016b  */
    /* JADX WARN: Code duplicated, block: B:91:0x016d  */
    /* JADX WARN: Code duplicated, block: B:94:0x0174  */
    /* JADX WARN: Code duplicated, block: B:96:0x017c  */
    /* JADX WARN: Code duplicated, block: B:99:0x0199  */
    public static final <T> void Crossfade(final Transition<T> transition, Modifier modifier, FiniteAnimationSpec<Float> finiteAnimationSpec, Function1<? super T, ? extends Object> function1, final Function3<? super T, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        FiniteAnimationSpec<Float> finiteAnimationSpec2;
        int i5;
        int i6;
        Function1<? super T, ? extends Object> function2;
        int i7;
        int i8;
        TweenSpec tweenSpecTween$default;
        AnonymousClass3 anonymousClass3;
        Object objRememberedValue;
        Object obj;
        SnapshotStateList snapshotStateList;
        Object objRememberedValue2;
        MutableScatterMap mutableScatterMap;
        SnapshotStateList snapshotStateList2;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM3454constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        SnapshotStateList snapshotStateList3;
        int size;
        int i9;
        final FiniteAnimationSpec<Float> finiteAnimationSpec3;
        final Function1<? super T, ? extends Object> function4;
        Function2 function5;
        SnapshotStateList snapshotStateList4;
        Iterator<T> it;
        int i10;
        int size2;
        int i11;
        boolean z;
        Object objRememberedValue3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final Transition<T> transition2 = transition;
        Composer composerStartRestartGroup = composer.startRestartGroup(679005231);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Crossfade)P(3!1,2)104@4422L64,105@4508L61,137@5785L159:Crossfade.kt#xbi5r1");
        if ((i2 & Integer.MIN_VALUE) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(transition2) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i12 = i2 & 1;
        if (i12 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 2;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    finiteAnimationSpec2 = finiteAnimationSpec;
                    if (composerStartRestartGroup.changedInstance(finiteAnimationSpec2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 4;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        function2 = function1;
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i2 & 8) != 0) {
                        i3 |= 24576;
                    } else if ((i & 24576) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                        } else {
                            tweenSpecTween$default = finiteAnimationSpec2;
                        }
                        if (i6 != 0) {
                            anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                                @Override // kotlin.jvm.functions.Function1
                                public final T invoke(T t) {
                                    return t;
                                }
                            };
                        } else {
                            anonymousClass3 = function2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        obj = objRememberedValue;
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            SnapshotStateList snapshotStateListMutableStateListOf = SnapshotStateKt.mutableStateListOf();
                            snapshotStateListMutableStateListOf.add(transition2.getCurrentState());
                            composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf);
                            obj = snapshotStateListMutableStateListOf;
                        }
                        snapshotStateList = (SnapshotStateList) obj;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                            composerStartRestartGroup.startReplaceGroup(860660313);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "");
                            if (snapshotStateList.size() == 1 || !Intrinsics.areEqual(snapshotStateList.get(0), transition2.getTargetState())) {
                                composerStartRestartGroup.startReplaceGroup(860794667);
                                ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                                SnapshotStateList snapshotStateList5 = snapshotStateList;
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                                if ((i3 & 14) == 4) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                                if (!z || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Boolean invoke(T t) {
                                            return Boolean.valueOf(!Intrinsics.areEqual(t, transition2.getTargetState()));
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                CollectionsKt.removeAll((List) snapshotStateList5, (Function1) objRememberedValue3);
                                mutableScatterMap.clear();
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(860984945);
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(860990897);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        if (!mutableScatterMap.contains(transition2.getTargetState())) {
                            composerStartRestartGroup.startReplaceGroup(861052122);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                            snapshotStateList4 = snapshotStateList;
                            it = snapshotStateList4.iterator();
                            i10 = 0;
                            while (true) {
                                if (!it.hasNext()) {
                                    i10 = -1;
                                    break;
                                } else if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                    break;
                                } else {
                                    i10++;
                                }
                            }
                            if (i10 == -1) {
                                snapshotStateList.add(transition2.getTargetState());
                            } else {
                                snapshotStateList.set(i10, transition2.getTargetState());
                            }
                            mutableScatterMap.clear();
                            size2 = snapshotStateList4.size();
                            i11 = 0;
                            while (i11 < size2) {
                                T t = snapshotStateList4.get(i11);
                                mutableScatterMap.set(t, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t, function3), composerStartRestartGroup, 54));
                                i11++;
                                transition2 = transition;
                                snapshotStateList = snapshotStateList;
                            }
                            snapshotStateList2 = snapshotStateList;
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            snapshotStateList2 = snapshotStateList;
                            composerStartRestartGroup.startReplaceGroup(861812273);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                        composerStartRestartGroup.startReplaceGroup(-187482432);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        snapshotStateList3 = snapshotStateList2;
                        size = snapshotStateList3.size();
                        for (i9 = 0; i9 < size; i9++) {
                            T t2 = snapshotStateList3.get(i9);
                            composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t2));
                            ComposerKt.sourceInformation(composerStartRestartGroup, "");
                            function5 = (Function2) mutableScatterMap.get(t2);
                            if (function5 == null) {
                                composerStartRestartGroup.startReplaceGroup(821713034);
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1081871785);
                                ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                                function5.invoke(composerStartRestartGroup, 0);
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            composerStartRestartGroup.endMovableGroup();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        finiteAnimationSpec3 = tweenSpecTween$default;
                        function4 = anonymousClass3;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        finiteAnimationSpec3 = finiteAnimationSpec2;
                        function4 = function2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier3 = modifier2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
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
                                CrossfadeKt.Crossfade(transition, modifier3, finiteAnimationSpec3, function4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 3072;
                function2 = function1;
                if ((i2 & 8) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) == 9362) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                            @Override // kotlin.jvm.functions.Function1
                            public final T invoke(T t3) {
                                return t3;
                            }
                        };
                    } else {
                        anonymousClass3 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    obj = objRememberedValue;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        SnapshotStateList snapshotStateListMutableStateListOf2 = SnapshotStateKt.mutableStateListOf();
                        snapshotStateListMutableStateListOf2.add(transition2.getCurrentState());
                        composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf2);
                        obj = snapshotStateListMutableStateListOf2;
                    }
                    snapshotStateList = (SnapshotStateList) obj;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(860660313);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        if (snapshotStateList.size() == 1) {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList6 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t3) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t3, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t3) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t3, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList6, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList7 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t3) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t3, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t3) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t3, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList7, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860990897);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (!mutableScatterMap.contains(transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(861052122);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                        snapshotStateList4 = snapshotStateList;
                        it = snapshotStateList4.iterator();
                        i10 = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                i10 = -1;
                                break;
                            } else {
                                if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                    break;
                                    break;
                                }
                                i10++;
                            }
                        }
                        if (i10 == -1) {
                            snapshotStateList.add(transition2.getTargetState());
                        } else {
                            snapshotStateList.set(i10, transition2.getTargetState());
                        }
                        mutableScatterMap.clear();
                        size2 = snapshotStateList4.size();
                        i11 = 0;
                        while (i11 < size2) {
                            T t3 = snapshotStateList4.get(i11);
                            mutableScatterMap.set(t3, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t3, function3), composerStartRestartGroup, 54));
                            i11++;
                            transition2 = transition;
                            snapshotStateList = snapshotStateList;
                        }
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.startReplaceGroup(861812273);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                    composerStartRestartGroup.startReplaceGroup(-187482432);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    snapshotStateList3 = snapshotStateList2;
                    size = snapshotStateList3.size();
                    while (i9 < size) {
                        T t4 = snapshotStateList3.get(i9);
                        composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t4));
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        function5 = (Function2) mutableScatterMap.get(t4);
                        if (function5 == null) {
                            composerStartRestartGroup.startReplaceGroup(821713034);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1081871785);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                            function5.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endMovableGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    finiteAnimationSpec3 = tweenSpecTween$default;
                    function4 = anonymousClass3;
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                            @Override // kotlin.jvm.functions.Function1
                            public final T invoke(T t5) {
                                return t5;
                            }
                        };
                    } else {
                        anonymousClass3 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    obj = objRememberedValue;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        SnapshotStateList snapshotStateListMutableStateListOf3 = SnapshotStateKt.mutableStateListOf();
                        snapshotStateListMutableStateListOf3.add(transition2.getCurrentState());
                        composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf3);
                        obj = snapshotStateListMutableStateListOf3;
                    }
                    snapshotStateList = (SnapshotStateList) obj;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(860660313);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        if (snapshotStateList.size() == 1) {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList8 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t5) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t5, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t5) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t5, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList8, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList9 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t5) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t5, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t5) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t5, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList9, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860990897);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (!mutableScatterMap.contains(transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(861052122);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                        snapshotStateList4 = snapshotStateList;
                        it = snapshotStateList4.iterator();
                        i10 = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                i10 = -1;
                                break;
                            } else {
                                if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                    break;
                                    break;
                                }
                                i10++;
                            }
                        }
                        if (i10 == -1) {
                            snapshotStateList.add(transition2.getTargetState());
                        } else {
                            snapshotStateList.set(i10, transition2.getTargetState());
                        }
                        mutableScatterMap.clear();
                        size2 = snapshotStateList4.size();
                        i11 = 0;
                        while (i11 < size2) {
                            T t5 = snapshotStateList4.get(i11);
                            mutableScatterMap.set(t5, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t5, function3), composerStartRestartGroup, 54));
                            i11++;
                            transition2 = transition;
                            snapshotStateList = snapshotStateList;
                        }
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.startReplaceGroup(861812273);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                    composerStartRestartGroup.startReplaceGroup(-187482432);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    snapshotStateList3 = snapshotStateList2;
                    size = snapshotStateList3.size();
                    while (i9 < size) {
                        T t6 = snapshotStateList3.get(i9);
                        composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t6));
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        function5 = (Function2) mutableScatterMap.get(t6);
                        if (function5 == null) {
                            composerStartRestartGroup.startReplaceGroup(821713034);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1081871785);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                            function5.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endMovableGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    finiteAnimationSpec3 = tweenSpecTween$default;
                    function4 = anonymousClass3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier4 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
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
                            CrossfadeKt.Crossfade(transition, modifier4, finiteAnimationSpec3, function4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            finiteAnimationSpec2 = finiteAnimationSpec;
            i6 = i2 & 4;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    function2 = function1;
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i2 & 8) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) == 9362) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                            @Override // kotlin.jvm.functions.Function1
                            public final T invoke(T t7) {
                                return t7;
                            }
                        };
                    } else {
                        anonymousClass3 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    obj = objRememberedValue;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        SnapshotStateList snapshotStateListMutableStateListOf4 = SnapshotStateKt.mutableStateListOf();
                        snapshotStateListMutableStateListOf4.add(transition2.getCurrentState());
                        composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf4);
                        obj = snapshotStateListMutableStateListOf4;
                    }
                    snapshotStateList = (SnapshotStateList) obj;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(860660313);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        if (snapshotStateList.size() == 1) {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList10 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t7) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t7, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t7) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t7, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList10, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList11 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t7) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t7, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t7) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t7, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList11, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860990897);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (!mutableScatterMap.contains(transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(861052122);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                        snapshotStateList4 = snapshotStateList;
                        it = snapshotStateList4.iterator();
                        i10 = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                i10 = -1;
                                break;
                            } else {
                                if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                    break;
                                    break;
                                }
                                i10++;
                            }
                        }
                        if (i10 == -1) {
                            snapshotStateList.add(transition2.getTargetState());
                        } else {
                            snapshotStateList.set(i10, transition2.getTargetState());
                        }
                        mutableScatterMap.clear();
                        size2 = snapshotStateList4.size();
                        i11 = 0;
                        while (i11 < size2) {
                            T t7 = snapshotStateList4.get(i11);
                            mutableScatterMap.set(t7, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t7, function3), composerStartRestartGroup, 54));
                            i11++;
                            transition2 = transition;
                            snapshotStateList = snapshotStateList;
                        }
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.startReplaceGroup(861812273);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                    composerStartRestartGroup.startReplaceGroup(-187482432);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    snapshotStateList3 = snapshotStateList2;
                    size = snapshotStateList3.size();
                    while (i9 < size) {
                        T t8 = snapshotStateList3.get(i9);
                        composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t8));
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        function5 = (Function2) mutableScatterMap.get(t8);
                        if (function5 == null) {
                            composerStartRestartGroup.startReplaceGroup(821713034);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1081871785);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                            function5.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endMovableGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    finiteAnimationSpec3 = tweenSpecTween$default;
                    function4 = anonymousClass3;
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                            @Override // kotlin.jvm.functions.Function1
                            public final T invoke(T t9) {
                                return t9;
                            }
                        };
                    } else {
                        anonymousClass3 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    obj = objRememberedValue;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        SnapshotStateList snapshotStateListMutableStateListOf5 = SnapshotStateKt.mutableStateListOf();
                        snapshotStateListMutableStateListOf5.add(transition2.getCurrentState());
                        composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf5);
                        obj = snapshotStateListMutableStateListOf5;
                    }
                    snapshotStateList = (SnapshotStateList) obj;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(860660313);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        if (snapshotStateList.size() == 1) {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList12 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t9) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t9, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t9) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t9, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList12, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList13 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t9) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t9, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t9) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t9, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList13, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860990897);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (!mutableScatterMap.contains(transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(861052122);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                        snapshotStateList4 = snapshotStateList;
                        it = snapshotStateList4.iterator();
                        i10 = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                i10 = -1;
                                break;
                            } else {
                                if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                    break;
                                    break;
                                }
                                i10++;
                            }
                        }
                        if (i10 == -1) {
                            snapshotStateList.add(transition2.getTargetState());
                        } else {
                            snapshotStateList.set(i10, transition2.getTargetState());
                        }
                        mutableScatterMap.clear();
                        size2 = snapshotStateList4.size();
                        i11 = 0;
                        while (i11 < size2) {
                            T t9 = snapshotStateList4.get(i11);
                            mutableScatterMap.set(t9, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t9, function3), composerStartRestartGroup, 54));
                            i11++;
                            transition2 = transition;
                            snapshotStateList = snapshotStateList;
                        }
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.startReplaceGroup(861812273);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                    composerStartRestartGroup.startReplaceGroup(-187482432);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    snapshotStateList3 = snapshotStateList2;
                    size = snapshotStateList3.size();
                    while (i9 < size) {
                        T t10 = snapshotStateList3.get(i9);
                        composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t10));
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        function5 = (Function2) mutableScatterMap.get(t10);
                        if (function5 == null) {
                            composerStartRestartGroup.startReplaceGroup(821713034);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1081871785);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                            function5.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endMovableGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    finiteAnimationSpec3 = tweenSpecTween$default;
                    function4 = anonymousClass3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
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
                            CrossfadeKt.Crossfade(transition, modifier5, finiteAnimationSpec3, function4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            function2 = function1;
            if ((i2 & 8) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
            if ((i3 & 9363) == 9362) {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                        @Override // kotlin.jvm.functions.Function1
                        public final T invoke(T t11) {
                            return t11;
                        }
                    };
                } else {
                    anonymousClass3 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                obj = objRememberedValue;
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    SnapshotStateList snapshotStateListMutableStateListOf6 = SnapshotStateKt.mutableStateListOf();
                    snapshotStateListMutableStateListOf6.add(transition2.getCurrentState());
                    composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf6);
                    obj = snapshotStateListMutableStateListOf6;
                }
                snapshotStateList = (SnapshotStateList) obj;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(860660313);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    if (snapshotStateList.size() == 1) {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList14 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t11) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t11, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t11) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t11, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList14, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList15 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t11) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t11, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t11) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t11, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList15, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(860990897);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (!mutableScatterMap.contains(transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(861052122);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                    snapshotStateList4 = snapshotStateList;
                    it = snapshotStateList4.iterator();
                    i10 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i10 = -1;
                            break;
                        } else {
                            if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                break;
                                break;
                            }
                            i10++;
                        }
                    }
                    if (i10 == -1) {
                        snapshotStateList.add(transition2.getTargetState());
                    } else {
                        snapshotStateList.set(i10, transition2.getTargetState());
                    }
                    mutableScatterMap.clear();
                    size2 = snapshotStateList4.size();
                    i11 = 0;
                    while (i11 < size2) {
                        T t11 = snapshotStateList4.get(i11);
                        mutableScatterMap.set(t11, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t11, function3), composerStartRestartGroup, 54));
                        i11++;
                        transition2 = transition;
                        snapshotStateList = snapshotStateList;
                    }
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.startReplaceGroup(861812273);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                composerStartRestartGroup.startReplaceGroup(-187482432);
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                snapshotStateList3 = snapshotStateList2;
                size = snapshotStateList3.size();
                while (i9 < size) {
                    T t12 = snapshotStateList3.get(i9);
                    composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t12));
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    function5 = (Function2) mutableScatterMap.get(t12);
                    if (function5 == null) {
                        composerStartRestartGroup.startReplaceGroup(821713034);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1081871785);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                        function5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endMovableGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                finiteAnimationSpec3 = tweenSpecTween$default;
                function4 = anonymousClass3;
            } else {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                        @Override // kotlin.jvm.functions.Function1
                        public final T invoke(T t13) {
                            return t13;
                        }
                    };
                } else {
                    anonymousClass3 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                obj = objRememberedValue;
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    SnapshotStateList snapshotStateListMutableStateListOf7 = SnapshotStateKt.mutableStateListOf();
                    snapshotStateListMutableStateListOf7.add(transition2.getCurrentState());
                    composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf7);
                    obj = snapshotStateListMutableStateListOf7;
                }
                snapshotStateList = (SnapshotStateList) obj;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(860660313);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    if (snapshotStateList.size() == 1) {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList16 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t13) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t13, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t13) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t13, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList16, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList17 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t13) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t13, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t13) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t13, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList17, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(860990897);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (!mutableScatterMap.contains(transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(861052122);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                    snapshotStateList4 = snapshotStateList;
                    it = snapshotStateList4.iterator();
                    i10 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i10 = -1;
                            break;
                        } else {
                            if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                break;
                                break;
                            }
                            i10++;
                        }
                    }
                    if (i10 == -1) {
                        snapshotStateList.add(transition2.getTargetState());
                    } else {
                        snapshotStateList.set(i10, transition2.getTargetState());
                    }
                    mutableScatterMap.clear();
                    size2 = snapshotStateList4.size();
                    i11 = 0;
                    while (i11 < size2) {
                        T t13 = snapshotStateList4.get(i11);
                        mutableScatterMap.set(t13, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t13, function3), composerStartRestartGroup, 54));
                        i11++;
                        transition2 = transition;
                        snapshotStateList = snapshotStateList;
                    }
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.startReplaceGroup(861812273);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                composerStartRestartGroup.startReplaceGroup(-187482432);
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                snapshotStateList3 = snapshotStateList2;
                size = snapshotStateList3.size();
                while (i9 < size) {
                    T t14 = snapshotStateList3.get(i9);
                    composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t14));
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    function5 = (Function2) mutableScatterMap.get(t14);
                    if (function5 == null) {
                        composerStartRestartGroup.startReplaceGroup(821713034);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1081871785);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                        function5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endMovableGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                finiteAnimationSpec3 = tweenSpecTween$default;
                function4 = anonymousClass3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
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
                        CrossfadeKt.Crossfade(transition, modifier6, finiteAnimationSpec3, function4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 2;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                finiteAnimationSpec2 = finiteAnimationSpec;
                if (composerStartRestartGroup.changedInstance(finiteAnimationSpec2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 4;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    function2 = function1;
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i2 & 8) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) == 9362) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                            @Override // kotlin.jvm.functions.Function1
                            public final T invoke(T t15) {
                                return t15;
                            }
                        };
                    } else {
                        anonymousClass3 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    obj = objRememberedValue;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        SnapshotStateList snapshotStateListMutableStateListOf8 = SnapshotStateKt.mutableStateListOf();
                        snapshotStateListMutableStateListOf8.add(transition2.getCurrentState());
                        composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf8);
                        obj = snapshotStateListMutableStateListOf8;
                    }
                    snapshotStateList = (SnapshotStateList) obj;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(860660313);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        if (snapshotStateList.size() == 1) {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList18 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t15) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t15, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t15) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t15, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList18, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList19 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t15) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t15, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t15) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t15, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList19, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860990897);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (!mutableScatterMap.contains(transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(861052122);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                        snapshotStateList4 = snapshotStateList;
                        it = snapshotStateList4.iterator();
                        i10 = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                i10 = -1;
                                break;
                            } else {
                                if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                    break;
                                    break;
                                }
                                i10++;
                            }
                        }
                        if (i10 == -1) {
                            snapshotStateList.add(transition2.getTargetState());
                        } else {
                            snapshotStateList.set(i10, transition2.getTargetState());
                        }
                        mutableScatterMap.clear();
                        size2 = snapshotStateList4.size();
                        i11 = 0;
                        while (i11 < size2) {
                            T t15 = snapshotStateList4.get(i11);
                            mutableScatterMap.set(t15, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t15, function3), composerStartRestartGroup, 54));
                            i11++;
                            transition2 = transition;
                            snapshotStateList = snapshotStateList;
                        }
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.startReplaceGroup(861812273);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                    composerStartRestartGroup.startReplaceGroup(-187482432);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    snapshotStateList3 = snapshotStateList2;
                    size = snapshotStateList3.size();
                    while (i9 < size) {
                        T t16 = snapshotStateList3.get(i9);
                        composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t16));
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        function5 = (Function2) mutableScatterMap.get(t16);
                        if (function5 == null) {
                            composerStartRestartGroup.startReplaceGroup(821713034);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1081871785);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                            function5.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endMovableGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    finiteAnimationSpec3 = tweenSpecTween$default;
                    function4 = anonymousClass3;
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        tweenSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i6 != 0) {
                        anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                            @Override // kotlin.jvm.functions.Function1
                            public final T invoke(T t17) {
                                return t17;
                            }
                        };
                    } else {
                        anonymousClass3 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    obj = objRememberedValue;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        SnapshotStateList snapshotStateListMutableStateListOf9 = SnapshotStateKt.mutableStateListOf();
                        snapshotStateListMutableStateListOf9.add(transition2.getCurrentState());
                        composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf9);
                        obj = snapshotStateListMutableStateListOf9;
                    }
                    snapshotStateList = (SnapshotStateList) obj;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(860660313);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        if (snapshotStateList.size() == 1) {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList110 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t17) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t17, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t17) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t17, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList110, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(860794667);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                            SnapshotStateList snapshotStateList111 = snapshotStateList;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!z) {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t17) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t17, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(T t17) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t17, transition2.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CollectionsKt.removeAll((List) snapshotStateList111, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860990897);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (!mutableScatterMap.contains(transition2.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(861052122);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                        snapshotStateList4 = snapshotStateList;
                        it = snapshotStateList4.iterator();
                        i10 = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                i10 = -1;
                                break;
                            } else {
                                if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                    break;
                                    break;
                                }
                                i10++;
                            }
                        }
                        if (i10 == -1) {
                            snapshotStateList.add(transition2.getTargetState());
                        } else {
                            snapshotStateList.set(i10, transition2.getTargetState());
                        }
                        mutableScatterMap.clear();
                        size2 = snapshotStateList4.size();
                        i11 = 0;
                        while (i11 < size2) {
                            T t17 = snapshotStateList4.get(i11);
                            mutableScatterMap.set(t17, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t17, function3), composerStartRestartGroup, 54));
                            i11++;
                            transition2 = transition;
                            snapshotStateList = snapshotStateList;
                        }
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        snapshotStateList2 = snapshotStateList;
                        composerStartRestartGroup.startReplaceGroup(861812273);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy9 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                    composerStartRestartGroup.startReplaceGroup(-187482432);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    snapshotStateList3 = snapshotStateList2;
                    size = snapshotStateList3.size();
                    while (i9 < size) {
                        T t18 = snapshotStateList3.get(i9);
                        composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t18));
                        ComposerKt.sourceInformation(composerStartRestartGroup, "");
                        function5 = (Function2) mutableScatterMap.get(t18);
                        if (function5 == null) {
                            composerStartRestartGroup.startReplaceGroup(821713034);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1081871785);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                            function5.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endMovableGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    finiteAnimationSpec3 = tweenSpecTween$default;
                    function4 = anonymousClass3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier7 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
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
                            CrossfadeKt.Crossfade(transition, modifier7, finiteAnimationSpec3, function4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            function2 = function1;
            if ((i2 & 8) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
            if ((i3 & 9363) == 9362) {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                        @Override // kotlin.jvm.functions.Function1
                        public final T invoke(T t19) {
                            return t19;
                        }
                    };
                } else {
                    anonymousClass3 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                obj = objRememberedValue;
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    SnapshotStateList snapshotStateListMutableStateListOf10 = SnapshotStateKt.mutableStateListOf();
                    snapshotStateListMutableStateListOf10.add(transition2.getCurrentState());
                    composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf10);
                    obj = snapshotStateListMutableStateListOf10;
                }
                snapshotStateList = (SnapshotStateList) obj;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(860660313);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    if (snapshotStateList.size() == 1) {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList112 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t19) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t19, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t19) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t19, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList112, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList113 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t19) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t19, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t19) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t19, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList113, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(860990897);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (!mutableScatterMap.contains(transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(861052122);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                    snapshotStateList4 = snapshotStateList;
                    it = snapshotStateList4.iterator();
                    i10 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i10 = -1;
                            break;
                        } else {
                            if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                break;
                                break;
                            }
                            i10++;
                        }
                    }
                    if (i10 == -1) {
                        snapshotStateList.add(transition2.getTargetState());
                    } else {
                        snapshotStateList.set(i10, transition2.getTargetState());
                    }
                    mutableScatterMap.clear();
                    size2 = snapshotStateList4.size();
                    i11 = 0;
                    while (i11 < size2) {
                        T t19 = snapshotStateList4.get(i11);
                        mutableScatterMap.set(t19, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t19, function3), composerStartRestartGroup, 54));
                        i11++;
                        transition2 = transition;
                        snapshotStateList = snapshotStateList;
                    }
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.startReplaceGroup(861812273);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy10 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                composerStartRestartGroup.startReplaceGroup(-187482432);
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                snapshotStateList3 = snapshotStateList2;
                size = snapshotStateList3.size();
                while (i9 < size) {
                    T t110 = snapshotStateList3.get(i9);
                    composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t110));
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    function5 = (Function2) mutableScatterMap.get(t110);
                    if (function5 == null) {
                        composerStartRestartGroup.startReplaceGroup(821713034);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1081871785);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                        function5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endMovableGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                finiteAnimationSpec3 = tweenSpecTween$default;
                function4 = anonymousClass3;
            } else {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                        @Override // kotlin.jvm.functions.Function1
                        public final T invoke(T t111) {
                            return t111;
                        }
                    };
                } else {
                    anonymousClass3 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                obj = objRememberedValue;
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    SnapshotStateList snapshotStateListMutableStateListOf11 = SnapshotStateKt.mutableStateListOf();
                    snapshotStateListMutableStateListOf11.add(transition2.getCurrentState());
                    composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf11);
                    obj = snapshotStateListMutableStateListOf11;
                }
                snapshotStateList = (SnapshotStateList) obj;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(860660313);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    if (snapshotStateList.size() == 1) {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList114 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t111) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t111, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t111) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t111, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList114, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList115 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t111) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t111, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t111) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t111, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList115, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(860990897);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (!mutableScatterMap.contains(transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(861052122);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                    snapshotStateList4 = snapshotStateList;
                    it = snapshotStateList4.iterator();
                    i10 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i10 = -1;
                            break;
                        } else {
                            if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                break;
                                break;
                            }
                            i10++;
                        }
                    }
                    if (i10 == -1) {
                        snapshotStateList.add(transition2.getTargetState());
                    } else {
                        snapshotStateList.set(i10, transition2.getTargetState());
                    }
                    mutableScatterMap.clear();
                    size2 = snapshotStateList4.size();
                    i11 = 0;
                    while (i11 < size2) {
                        T t111 = snapshotStateList4.get(i11);
                        mutableScatterMap.set(t111, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t111, function3), composerStartRestartGroup, 54));
                        i11++;
                        transition2 = transition;
                        snapshotStateList = snapshotStateList;
                    }
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.startReplaceGroup(861812273);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy11 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                composerStartRestartGroup.startReplaceGroup(-187482432);
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                snapshotStateList3 = snapshotStateList2;
                size = snapshotStateList3.size();
                while (i9 < size) {
                    T t112 = snapshotStateList3.get(i9);
                    composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t112));
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    function5 = (Function2) mutableScatterMap.get(t112);
                    if (function5 == null) {
                        composerStartRestartGroup.startReplaceGroup(821713034);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1081871785);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                        function5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endMovableGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                finiteAnimationSpec3 = tweenSpecTween$default;
                function4 = anonymousClass3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier8 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
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
                        CrossfadeKt.Crossfade(transition, modifier8, finiteAnimationSpec3, function4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        finiteAnimationSpec2 = finiteAnimationSpec;
        i6 = i2 & 4;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                function2 = function1;
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i2 & 8) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
            if ((i3 & 9363) == 9362) {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                        @Override // kotlin.jvm.functions.Function1
                        public final T invoke(T t113) {
                            return t113;
                        }
                    };
                } else {
                    anonymousClass3 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                obj = objRememberedValue;
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    SnapshotStateList snapshotStateListMutableStateListOf12 = SnapshotStateKt.mutableStateListOf();
                    snapshotStateListMutableStateListOf12.add(transition2.getCurrentState());
                    composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf12);
                    obj = snapshotStateListMutableStateListOf12;
                }
                snapshotStateList = (SnapshotStateList) obj;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(860660313);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    if (snapshotStateList.size() == 1) {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList116 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t113) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t113, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t113) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t113, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList116, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList117 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t113) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t113, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t113) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t113, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList117, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(860990897);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (!mutableScatterMap.contains(transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(861052122);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                    snapshotStateList4 = snapshotStateList;
                    it = snapshotStateList4.iterator();
                    i10 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i10 = -1;
                            break;
                        } else {
                            if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                break;
                                break;
                            }
                            i10++;
                        }
                    }
                    if (i10 == -1) {
                        snapshotStateList.add(transition2.getTargetState());
                    } else {
                        snapshotStateList.set(i10, transition2.getTargetState());
                    }
                    mutableScatterMap.clear();
                    size2 = snapshotStateList4.size();
                    i11 = 0;
                    while (i11 < size2) {
                        T t113 = snapshotStateList4.get(i11);
                        mutableScatterMap.set(t113, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t113, function3), composerStartRestartGroup, 54));
                        i11++;
                        transition2 = transition;
                        snapshotStateList = snapshotStateList;
                    }
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.startReplaceGroup(861812273);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy12 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                composerStartRestartGroup.startReplaceGroup(-187482432);
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                snapshotStateList3 = snapshotStateList2;
                size = snapshotStateList3.size();
                while (i9 < size) {
                    T t114 = snapshotStateList3.get(i9);
                    composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t114));
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    function5 = (Function2) mutableScatterMap.get(t114);
                    if (function5 == null) {
                        composerStartRestartGroup.startReplaceGroup(821713034);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1081871785);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                        function5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endMovableGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                finiteAnimationSpec3 = tweenSpecTween$default;
                function4 = anonymousClass3;
            } else {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    tweenSpecTween$default = finiteAnimationSpec2;
                }
                if (i6 != 0) {
                    anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                        @Override // kotlin.jvm.functions.Function1
                        public final T invoke(T t115) {
                            return t115;
                        }
                    };
                } else {
                    anonymousClass3 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                obj = objRememberedValue;
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    SnapshotStateList snapshotStateListMutableStateListOf13 = SnapshotStateKt.mutableStateListOf();
                    snapshotStateListMutableStateListOf13.add(transition2.getCurrentState());
                    composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf13);
                    obj = snapshotStateListMutableStateListOf13;
                }
                snapshotStateList = (SnapshotStateList) obj;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(860660313);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    if (snapshotStateList.size() == 1) {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList118 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t115) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t115, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t115) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t115, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList118, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(860794667);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                        SnapshotStateList snapshotStateList119 = snapshotStateList;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t115) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t115, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(T t115) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t115, transition2.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CollectionsKt.removeAll((List) snapshotStateList119, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(860990897);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (!mutableScatterMap.contains(transition2.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(861052122);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                    snapshotStateList4 = snapshotStateList;
                    it = snapshotStateList4.iterator();
                    i10 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i10 = -1;
                            break;
                        } else {
                            if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                                break;
                                break;
                            }
                            i10++;
                        }
                    }
                    if (i10 == -1) {
                        snapshotStateList.add(transition2.getTargetState());
                    } else {
                        snapshotStateList.set(i10, transition2.getTargetState());
                    }
                    mutableScatterMap.clear();
                    size2 = snapshotStateList4.size();
                    i11 = 0;
                    while (i11 < size2) {
                        T t115 = snapshotStateList4.get(i11);
                        mutableScatterMap.set(t115, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t115, function3), composerStartRestartGroup, 54));
                        i11++;
                        transition2 = transition;
                        snapshotStateList = snapshotStateList;
                    }
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    snapshotStateList2 = snapshotStateList;
                    composerStartRestartGroup.startReplaceGroup(861812273);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy13 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
                composerStartRestartGroup.startReplaceGroup(-187482432);
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                snapshotStateList3 = snapshotStateList2;
                size = snapshotStateList3.size();
                while (i9 < size) {
                    T t116 = snapshotStateList3.get(i9);
                    composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t116));
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    function5 = (Function2) mutableScatterMap.get(t116);
                    if (function5 == null) {
                        composerStartRestartGroup.startReplaceGroup(821713034);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1081871785);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                        function5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endMovableGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                finiteAnimationSpec3 = tweenSpecTween$default;
                function4 = anonymousClass3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier9 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
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
                        CrossfadeKt.Crossfade(transition, modifier9, finiteAnimationSpec3, function4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        function2 = function1;
        if ((i2 & 8) != 0) {
            i3 |= 24576;
        } else if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i8 = 16384;
            } else {
                i8 = 8192;
            }
            i3 |= i8;
        }
        if ((i3 & 9363) == 9362) {
            if (i12 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i4 != 0) {
                tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            } else {
                tweenSpecTween$default = finiteAnimationSpec2;
            }
            if (i6 != 0) {
                anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                    @Override // kotlin.jvm.functions.Function1
                    public final T invoke(T t117) {
                        return t117;
                    }
                };
            } else {
                anonymousClass3 = function2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            obj = objRememberedValue;
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                SnapshotStateList snapshotStateListMutableStateListOf14 = SnapshotStateKt.mutableStateListOf();
                snapshotStateListMutableStateListOf14.add(transition2.getCurrentState());
                composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf14);
                obj = snapshotStateListMutableStateListOf14;
            }
            snapshotStateList = (SnapshotStateList) obj;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            mutableScatterMap = (MutableScatterMap) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                composerStartRestartGroup.startReplaceGroup(860660313);
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                if (snapshotStateList.size() == 1) {
                    composerStartRestartGroup.startReplaceGroup(860794667);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                    SnapshotStateList snapshotStateList1110 = snapshotStateList;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                    if ((i3 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(T t117) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t117, transition2.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(T t117) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t117, transition2.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CollectionsKt.removeAll((List) snapshotStateList1110, (Function1) objRememberedValue3);
                    mutableScatterMap.clear();
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(860794667);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                    SnapshotStateList snapshotStateList1111 = snapshotStateList;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                    if ((i3 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(T t117) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t117, transition2.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(T t117) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t117, transition2.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CollectionsKt.removeAll((List) snapshotStateList1111, (Function1) objRememberedValue3);
                    mutableScatterMap.clear();
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(860990897);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (!mutableScatterMap.contains(transition2.getTargetState())) {
                composerStartRestartGroup.startReplaceGroup(861052122);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                snapshotStateList4 = snapshotStateList;
                it = snapshotStateList4.iterator();
                i10 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i10 = -1;
                        break;
                    } else {
                        if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                            break;
                            break;
                        }
                        i10++;
                    }
                }
                if (i10 == -1) {
                    snapshotStateList.add(transition2.getTargetState());
                } else {
                    snapshotStateList.set(i10, transition2.getTargetState());
                }
                mutableScatterMap.clear();
                size2 = snapshotStateList4.size();
                i11 = 0;
                while (i11 < size2) {
                    T t117 = snapshotStateList4.get(i11);
                    mutableScatterMap.set(t117, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t117, function3), composerStartRestartGroup, 54));
                    i11++;
                    transition2 = transition;
                    snapshotStateList = snapshotStateList;
                }
                snapshotStateList2 = snapshotStateList;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                snapshotStateList2 = snapshotStateList;
                composerStartRestartGroup.startReplaceGroup(861812273);
                composerStartRestartGroup.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy14 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
            composerStartRestartGroup.startReplaceGroup(-187482432);
            ComposerKt.sourceInformation(composerStartRestartGroup, "");
            snapshotStateList3 = snapshotStateList2;
            size = snapshotStateList3.size();
            while (i9 < size) {
                T t118 = snapshotStateList3.get(i9);
                composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t118));
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                function5 = (Function2) mutableScatterMap.get(t118);
                if (function5 == null) {
                    composerStartRestartGroup.startReplaceGroup(821713034);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1081871785);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                    function5.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endMovableGroup();
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            finiteAnimationSpec3 = tweenSpecTween$default;
            function4 = anonymousClass3;
        } else {
            if (i12 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i4 != 0) {
                tweenSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            } else {
                tweenSpecTween$default = finiteAnimationSpec2;
            }
            if (i6 != 0) {
                anonymousClass3 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.3
                    @Override // kotlin.jvm.functions.Function1
                    public final T invoke(T t119) {
                        return t119;
                    }
                };
            } else {
                anonymousClass3 = function2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(679005231, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:103)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274683025, "CC(remember):Crossfade.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            obj = objRememberedValue;
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                SnapshotStateList snapshotStateListMutableStateListOf15 = SnapshotStateKt.mutableStateListOf();
                snapshotStateListMutableStateListOf15.add(transition2.getCurrentState());
                composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf15);
                obj = snapshotStateListMutableStateListOf15;
            }
            snapshotStateList = (SnapshotStateList) obj;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274685774, "CC(remember):Crossfade.kt#9igjgp");
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            mutableScatterMap = (MutableScatterMap) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                composerStartRestartGroup.startReplaceGroup(860660313);
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                if (snapshotStateList.size() == 1) {
                    composerStartRestartGroup.startReplaceGroup(860794667);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                    SnapshotStateList snapshotStateList1112 = snapshotStateList;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                    if ((i3 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(T t119) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t119, transition2.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(T t119) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t119, transition2.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CollectionsKt.removeAll((List) snapshotStateList1112, (Function1) objRememberedValue3);
                    mutableScatterMap.clear();
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(860794667);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "110@4883L21");
                    SnapshotStateList snapshotStateList1113 = snapshotStateList;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1274697734, "CC(remember):Crossfade.kt#9igjgp");
                    if ((i3 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(T t119) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t119, transition2.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(T t119) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t119, transition2.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CollectionsKt.removeAll((List) snapshotStateList1113, (Function1) objRememberedValue3);
                    mutableScatterMap.clear();
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(860990897);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (!mutableScatterMap.contains(transition2.getTargetState())) {
                composerStartRestartGroup.startReplaceGroup(861052122);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*126@5458L305");
                snapshotStateList4 = snapshotStateList;
                it = snapshotStateList4.iterator();
                i10 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i10 = -1;
                        break;
                    } else {
                        if (Intrinsics.areEqual(anonymousClass3.invoke(it.next()), anonymousClass3.invoke(transition2.getTargetState()))) {
                            break;
                            break;
                        }
                        i10++;
                    }
                }
                if (i10 == -1) {
                    snapshotStateList.add(transition2.getTargetState());
                } else {
                    snapshotStateList.set(i10, transition2.getTargetState());
                }
                mutableScatterMap.clear();
                size2 = snapshotStateList4.size();
                i11 = 0;
                while (i11 < size2) {
                    T t119 = snapshotStateList4.get(i11);
                    mutableScatterMap.set(t119, ComposableLambdaKt.rememberComposableLambda(-1426421288, true, new CrossfadeKt$Crossfade$5$1(transition2, tweenSpecTween$default, t119, function3), composerStartRestartGroup, 54));
                    i11++;
                    transition2 = transition;
                    snapshotStateList = snapshotStateList;
                }
                snapshotStateList2 = snapshotStateList;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                snapshotStateList2 = snapshotStateList;
                composerStartRestartGroup.startReplaceGroup(861812273);
                composerStartRestartGroup.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy15 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1517004430, "C:Crossfade.kt#xbi5r1");
            composerStartRestartGroup.startReplaceGroup(-187482432);
            ComposerKt.sourceInformation(composerStartRestartGroup, "");
            snapshotStateList3 = snapshotStateList2;
            size = snapshotStateList3.size();
            while (i9 < size) {
                T t1110 = snapshotStateList3.get(i9);
                composerStartRestartGroup.startMovableGroup(-1081873445, anonymousClass3.invoke(t1110));
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                function5 = (Function2) mutableScatterMap.get(t1110);
                if (function5 == null) {
                    composerStartRestartGroup.startReplaceGroup(821713034);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1081871785);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "140@5906L8");
                    function5.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endMovableGroup();
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            finiteAnimationSpec3 = tweenSpecTween$default;
            function4 = anonymousClass3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier10 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
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
                    CrossfadeKt.Crossfade(transition, modifier10, finiteAnimationSpec3, function4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }
}
