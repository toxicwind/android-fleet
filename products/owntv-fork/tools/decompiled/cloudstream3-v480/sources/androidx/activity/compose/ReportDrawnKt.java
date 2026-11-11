package androidx.activity.compose;

import androidx.activity.FullyDrawnReporter;
import androidx.activity.FullyDrawnReporterOwner;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ReportDrawn.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u001b\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a\r\u0010\u0006\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0007\u001a+\u0010\b\u001a\u00020\u00012\u001c\u0010\t\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\nH\u0007¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"ReportDrawnWhen", "", "predicate", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "ReportDrawn", "(Landroidx/compose/runtime/Composer;I)V", "ReportDrawnAfter", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "activity-compose"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ReportDrawnKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ReportDrawn$lambda$0$0() {
        return true;
    }

    static final Unit ReportDrawn$lambda$1(int i, Composer composer, int i2) {
        ReportDrawn(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ReportDrawnAfter$lambda$0(Function1 function1, int i, Composer composer, int i2) {
        ReportDrawnAfter(function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ReportDrawnAfter$lambda$2(Function1 function1, int i, Composer composer, int i2) {
        ReportDrawnAfter(function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ReportDrawnWhen$lambda$0(Function0 function0, int i, Composer composer, int i2) {
        ReportDrawnWhen(function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ReportDrawnWhen$lambda$2(Function0 function0, int i, Composer composer, int i2) {
        ReportDrawnWhen(function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ReportDrawnWhen(final Function0<Boolean> function0, Composer composer, final int i) {
        int i2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function2<? super Composer, ? super Integer, Unit> function2;
        final FullyDrawnReporter fullyDrawnReporter;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2047119994);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ReportDrawnWhen)N(predicate)117@4402L7,118@4492L263,118@4444L311:ReportDrawn.kt#q1dkbc");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2047119994, i2, -1, "androidx.activity.compose.ReportDrawnWhen (ReportDrawn.kt:116)");
            }
            FullyDrawnReporterOwner current = LocalFullyDrawnReporterOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
            if (current == null || (fullyDrawnReporter = current.getFullyDrawnReporter()) == null) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                } else {
                    function2 = new Function2() { // from class: androidx.activity.compose.ReportDrawnKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return ReportDrawnKt.ReportDrawnWhen$lambda$0(function0, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    };
                }
            } else {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -187286131, "CC(remember):ReportDrawn.kt#9igjgp");
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(fullyDrawnReporter) | ((i2 & 14) == 4);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: androidx.activity.compose.ReportDrawnKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ReportDrawnKt.ReportDrawnWhen$lambda$1$0(fullyDrawnReporter, function0, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(fullyDrawnReporter, function0, (Function1) objRememberedValue, composerStartRestartGroup, (i2 << 3) & 112);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup.updateScope(function2);
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            function2 = new Function2() { // from class: androidx.activity.compose.ReportDrawnKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ReportDrawnKt.ReportDrawnWhen$lambda$2(function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            scopeUpdateScopeEndRestartGroup.updateScope(function2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult ReportDrawnWhen$lambda$1$0(FullyDrawnReporter fullyDrawnReporter, Function0 function0, DisposableEffectScope disposableEffectScope) {
        if (!fullyDrawnReporter.isFullyDrawnReported()) {
            final ReportDrawnComposition reportDrawnComposition = new ReportDrawnComposition(fullyDrawnReporter, function0);
            return new DisposableEffectResult() { // from class: androidx.activity.compose.ReportDrawnKt$ReportDrawnWhen$lambda$1$0$$inlined$onDispose$2
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    reportDrawnComposition.removeReporter();
                }
            };
        }
        return new DisposableEffectResult() { // from class: androidx.activity.compose.ReportDrawnKt$ReportDrawnWhen$lambda$1$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
            }
        };
    }

    public static final void ReportDrawn(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1357012904);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ReportDrawn)135@5039L8,135@5023L24:ReportDrawn.kt#q1dkbc");
        if (composerStartRestartGroup.shouldExecute(i != 0, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1357012904, i, -1, "androidx.activity.compose.ReportDrawn (ReportDrawn.kt:135)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1580658592, "CC(remember):ReportDrawn.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: androidx.activity.compose.ReportDrawnKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(ReportDrawnKt.ReportDrawn$lambda$0$0());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ReportDrawnWhen((Function0) objRememberedValue, composerStartRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.activity.compose.ReportDrawnKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ReportDrawnKt.ReportDrawn$lambda$1(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ReportDrawnAfter(final Function1<? super Continuation<? super Unit>, ? extends Object> function1, Composer composer, final int i) {
        int i2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function2<? super Composer, ? super Integer, Unit> function2;
        FullyDrawnReporter fullyDrawnReporter;
        Composer composerStartRestartGroup = composer.startRestartGroup(945311272);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ReportDrawnAfter)N(block)149@5544L7,150@5628L48,150@5586L90:ReportDrawn.kt#q1dkbc");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(945311272, i2, -1, "androidx.activity.compose.ReportDrawnAfter (ReportDrawn.kt:148)");
            }
            FullyDrawnReporterOwner current = LocalFullyDrawnReporterOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
            if (current == null || (fullyDrawnReporter = current.getFullyDrawnReporter()) == null) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                } else {
                    function2 = new Function2() { // from class: androidx.activity.compose.ReportDrawnKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return ReportDrawnKt.ReportDrawnAfter$lambda$0(function1, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    };
                }
            } else {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2088928168, "CC(remember):ReportDrawn.kt#9igjgp");
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(fullyDrawnReporter) | composerStartRestartGroup.changedInstance(function1);
                ReportDrawnKt$ReportDrawnAfter$1$1 reportDrawnKt$ReportDrawnAfter$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || reportDrawnKt$ReportDrawnAfter$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    reportDrawnKt$ReportDrawnAfter$1$1RememberedValue = new ReportDrawnKt$ReportDrawnAfter$1$1(fullyDrawnReporter, function1, null);
                    composerStartRestartGroup.updateRememberedValue(reportDrawnKt$ReportDrawnAfter$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(function1, fullyDrawnReporter, (Function2) reportDrawnKt$ReportDrawnAfter$1$1RememberedValue, composerStartRestartGroup, i2 & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup.updateScope(function2);
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            function2 = new Function2() { // from class: androidx.activity.compose.ReportDrawnKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ReportDrawnKt.ReportDrawnAfter$lambda$2(function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            scopeUpdateScopeEndRestartGroup.updateScope(function2);
        }
    }
}
