package androidx.activity.compose;

import androidx.activity.ActivityFlags;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.compose.internal.BackHandlerDispatcherCompat;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.lifecycle.compose.LifecycleEffectKt;
import androidx.lifecycle.compose.LifecycleStartStopEffectScope;
import androidx.lifecycle.compose.LifecycleStopOrDisposeEffectResult;
import androidx.navigationevent.NavigationEventDispatcher;
import androidx.navigationevent.NavigationEventDispatcherOwner;
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: BackHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"BackHandler", "", "enabled", "", "onBack", "Lkotlin/Function0;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "activity-compose"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BackHandlerKt {
    static final Unit BackHandler$lambda$7(boolean z, Function0 function0, int i, int i2, Composer composer, int i3) {
        BackHandler(z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void BackHandler(boolean z, final Function0<Unit> function0, Composer composer, final int i, final int i2) {
        boolean z2;
        int i3;
        final boolean z3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-361453782);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BackHandler)N(enabled,onBack)110@5097L7,119@5502L427,128@5954L27,130@6008L115:BackHandler.kt#q1dkbc");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            z2 = z;
        } else if ((i & 6) == 0) {
            z2 = z;
            i3 = (composerStartRestartGroup.changed(z2) ? 4 : 2) | i;
        } else {
            z2 = z;
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if (!composerStartRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
            z3 = z2;
        } else {
            z3 = i4 != 0 ? true : z2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-361453782, i3, -1, "androidx.activity.compose.BackHandler (BackHandler.kt:107)");
            }
            Object current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalNavigationEventDispatcherOwner.$stable);
            if (current != null) {
                composerStartRestartGroup.startReplaceGroup(535271790);
            } else {
                composerStartRestartGroup.startReplaceGroup(535274673);
                ComposerKt.sourceInformation(composerStartRestartGroup, "111@5154L7");
                current = LocalOnBackPressedDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (current == null) {
                throw new IllegalStateException("No NavigationEventDispatcherOwner was provided via LocalNavigationEventDispatcherOwner and no OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner. Please provide one of the two.".toString());
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 535286229, "CC(remember):BackHandler.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(current);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                NavigationEventDispatcherOwner navigationEventDispatcherOwner = current instanceof NavigationEventDispatcherOwner ? (NavigationEventDispatcherOwner) current : null;
                NavigationEventDispatcher navigationEventDispatcher = navigationEventDispatcherOwner != null ? navigationEventDispatcherOwner.getNavigationEventDispatcher() : null;
                OnBackPressedDispatcherOwner onBackPressedDispatcherOwner = current instanceof OnBackPressedDispatcherOwner ? (OnBackPressedDispatcherOwner) current : null;
                objRememberedValue = new BackHandlerDispatcherCompat(navigationEventDispatcher, onBackPressedDispatcherOwner != null ? onBackPressedDispatcherOwner.getOnBackPressedDispatcher() : null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final BackHandlerDispatcherCompat backHandlerDispatcherCompat = (BackHandlerDispatcherCompat) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long currentCompositeKeyHashCode = ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 535302109, "CC(remember):BackHandler.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(backHandlerDispatcherCompat) | composerStartRestartGroup.changed(currentCompositeKeyHashCode);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new ComposeBackHandler(new BackHandlerInfo(current, currentCompositeKeyHashCode));
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final ComposeBackHandler composeBackHandler = (ComposeBackHandler) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ActivityFlags.isOnBackPressedLifecycleOrderMaintained) {
                composerStartRestartGroup.startReplaceGroup(-585307852);
                ComposerKt.sourceInformation(composerStartRestartGroup, "136@6310L43,136@6299L54,140@6579L117,140@6540L156,145@6744L114,145@6706L152");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 535311701, "CC(remember):BackHandler.kt#9igjgp");
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(composeBackHandler) | ((i3 & 112) == 32);
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: androidx.activity.compose.BackHandlerKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BackHandlerKt.BackHandler$lambda$2$0(composeBackHandler, function0);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.SideEffect((Function0) objRememberedValue3, composerStartRestartGroup, 0);
                Boolean boolValueOf = Boolean.valueOf(z3);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 535320383, "CC(remember):BackHandler.kt#9igjgp");
                int i5 = i3 & 14;
                boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(composeBackHandler) | (i5 == 4);
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function1() { // from class: androidx.activity.compose.BackHandlerKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BackHandlerKt.BackHandler$lambda$3$0(composeBackHandler, z3, (LifecycleStartStopEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                LifecycleEffectKt.LifecycleStartEffect(boolValueOf, composeBackHandler, null, (Function1) objRememberedValue4, composerStartRestartGroup, i5, 4);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 535325660, "CC(remember):BackHandler.kt#9igjgp");
                boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(backHandlerDispatcherCompat) | composerStartRestartGroup.changedInstance(composeBackHandler);
                Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = new Function1() { // from class: androidx.activity.compose.BackHandlerKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BackHandlerKt.BackHandler$lambda$4$0(backHandlerDispatcherCompat, composeBackHandler, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(backHandlerDispatcherCompat, composeBackHandler, (Function1) objRememberedValue5, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-584634160);
                ComposerKt.sourceInformation(composerStartRestartGroup, "151@6992L107,151@6981L118,158@7328L120,158@7286L162");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 535333589, "CC(remember):BackHandler.kt#9igjgp");
                boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(composeBackHandler) | ((i3 & 14) == 4) | ((i3 & 112) == 32);
                Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function0() { // from class: androidx.activity.compose.BackHandlerKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BackHandlerKt.BackHandler$lambda$5$0(composeBackHandler, z3, function0);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.SideEffect((Function0) objRememberedValue6, composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 535344354, "CC(remember):BackHandler.kt#9igjgp");
                boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(backHandlerDispatcherCompat) | composerStartRestartGroup.changedInstance(composeBackHandler);
                Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance5 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = new Function1() { // from class: androidx.activity.compose.BackHandlerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BackHandlerKt.BackHandler$lambda$6$0(backHandlerDispatcherCompat, composeBackHandler, (LifecycleStartStopEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                LifecycleEffectKt.LifecycleStartEffect(backHandlerDispatcherCompat, composeBackHandler, null, (Function1) objRememberedValue7, composerStartRestartGroup, 0, 4);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.activity.compose.BackHandlerKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BackHandlerKt.BackHandler$lambda$7(z3, function0, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackHandler$lambda$2$0(ComposeBackHandler composeBackHandler, Function0 function0) {
        composeBackHandler.setCurrentOnBackCompleted(function0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LifecycleStopOrDisposeEffectResult BackHandler$lambda$3$0(final ComposeBackHandler composeBackHandler, boolean z, final LifecycleStartStopEffectScope lifecycleStartStopEffectScope) {
        composeBackHandler.setBackEnabled(z);
        return new LifecycleStopOrDisposeEffectResult() { // from class: androidx.activity.compose.BackHandlerKt$BackHandler$lambda$3$0$$inlined$onStopOrDispose$1
            @Override // androidx.lifecycle.compose.LifecycleStopOrDisposeEffectResult
            public void runStopOrDisposeEffect() {
                LifecycleStartStopEffectScope lifecycleStartStopEffectScope2 = lifecycleStartStopEffectScope;
                composeBackHandler.setBackEnabled(false);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult BackHandler$lambda$4$0(final BackHandlerDispatcherCompat backHandlerDispatcherCompat, final ComposeBackHandler composeBackHandler, DisposableEffectScope disposableEffectScope) {
        backHandlerDispatcherCompat.addHandler(composeBackHandler);
        return new DisposableEffectResult() { // from class: androidx.activity.compose.BackHandlerKt$BackHandler$lambda$4$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() throws Exception {
                backHandlerDispatcherCompat.removeHandler(composeBackHandler);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackHandler$lambda$5$0(ComposeBackHandler composeBackHandler, boolean z, Function0 function0) {
        composeBackHandler.setBackEnabled(z);
        composeBackHandler.setCurrentOnBackCompleted(function0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LifecycleStopOrDisposeEffectResult BackHandler$lambda$6$0(final BackHandlerDispatcherCompat backHandlerDispatcherCompat, final ComposeBackHandler composeBackHandler, final LifecycleStartStopEffectScope lifecycleStartStopEffectScope) {
        backHandlerDispatcherCompat.addHandler(composeBackHandler);
        return new LifecycleStopOrDisposeEffectResult() { // from class: androidx.activity.compose.BackHandlerKt$BackHandler$lambda$6$0$$inlined$onStopOrDispose$1
            @Override // androidx.lifecycle.compose.LifecycleStopOrDisposeEffectResult
            public void runStopOrDisposeEffect() throws Exception {
                LifecycleStartStopEffectScope lifecycleStartStopEffectScope2 = lifecycleStartStopEffectScope;
                backHandlerDispatcherCompat.removeHandler(composeBackHandler);
            }
        };
    }
}
