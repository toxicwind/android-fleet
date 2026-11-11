package androidx.lifecycle.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: RememberLifecycleOwner.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"rememberLifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "maxLifecycle", "Landroidx/lifecycle/Lifecycle$State;", "parent", "(Landroidx/lifecycle/Lifecycle$State;Landroidx/lifecycle/LifecycleOwner;Landroidx/compose/runtime/Composer;II)Landroidx/lifecycle/LifecycleOwner;", "lifecycle-runtime-compose"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RememberLifecycleOwnerKt {
    public static final LifecycleOwner rememberLifecycleOwner(Lifecycle.State state, final LifecycleOwner lifecycleOwner, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -1501509168, "C(rememberLifecycleOwner)N(maxLifecycle,parent)77@3569L7,79@3628L44,82@3787L899,82@3741L945,107@4812L68,107@4762L118:RememberLifecycleOwner.kt#2vxrgp");
        if ((i2 & 1) != 0) {
            state = Lifecycle.State.RESUMED;
        }
        if ((i2 & 2) != 0) {
            ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localLifecycleOwner);
            ComposerKt.sourceInformationMarkerEnd(composer);
            lifecycleOwner = (LifecycleOwner) objConsume;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1501509168, i, -1, "androidx.lifecycle.compose.rememberLifecycleOwner (RememberLifecycleOwner.kt:78)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 158706556, "CC(remember):RememberLifecycleOwner.kt#9igjgp");
        boolean zChanged = composer.changed(lifecycleOwner);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new ComposeLifecycleOwner();
            composer.updateRememberedValue(objRememberedValue);
        }
        final ComposeLifecycleOwner composeLifecycleOwner = (ComposeLifecycleOwner) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 158712499, "CC(remember):RememberLifecycleOwner.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(composeLifecycleOwner) | composer.changedInstance(lifecycleOwner);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: androidx.lifecycle.compose.RememberLifecycleOwnerKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RememberLifecycleOwnerKt.rememberLifecycleOwner$lambda$1$0(lifecycleOwner, composeLifecycleOwner, (DisposableEffectScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.DisposableEffect(composeLifecycleOwner, lifecycleOwner, (Function1) objRememberedValue2, composer, i & 112);
        ComposerKt.sourceInformationMarkerStart(composer, 158744468, "CC(remember):RememberLifecycleOwner.kt#9igjgp");
        boolean zChangedInstance2 = composer.changedInstance(composeLifecycleOwner) | ((((i & 14) ^ 6) > 4 && composer.changed(state.ordinal())) || (i & 6) == 4);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = (Function2) new RememberLifecycleOwnerKt$rememberLifecycleOwner$2$1(composeLifecycleOwner, state, null);
            composer.updateRememberedValue(objRememberedValue3);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.LaunchedEffect(composeLifecycleOwner, state, (Function2) objRememberedValue3, composer, (i << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return composeLifecycleOwner;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult rememberLifecycleOwner$lambda$1$0(final LifecycleOwner lifecycleOwner, final ComposeLifecycleOwner composeLifecycleOwner, DisposableEffectScope disposableEffectScope) {
        Lifecycle lifecycle;
        final LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.lifecycle.compose.RememberLifecycleOwnerKt$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                composeLifecycleOwner.handleLifecycleEvent(event);
            }
        };
        if (lifecycleOwner != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
            lifecycle.addObserver(lifecycleEventObserver);
        }
        if (lifecycleOwner == null) {
            composeLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }
        return new DisposableEffectResult() { // from class: androidx.lifecycle.compose.RememberLifecycleOwnerKt$rememberLifecycleOwner$lambda$1$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Lifecycle lifecycle2;
                LifecycleOwner lifecycleOwner2 = lifecycleOwner;
                if (lifecycleOwner2 != null && (lifecycle2 = lifecycleOwner2.getLifecycle()) != null) {
                    lifecycle2.removeObserver(lifecycleEventObserver);
                }
                composeLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            }
        };
    }
}
