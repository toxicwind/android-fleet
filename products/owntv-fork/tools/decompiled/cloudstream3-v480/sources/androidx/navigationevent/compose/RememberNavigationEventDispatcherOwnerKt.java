package androidx.navigationevent.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.navigationevent.NavigationEventDispatcher;
import androidx.navigationevent.NavigationEventDispatcherOwner;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: RememberNavigationEventDispatcherOwner.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"rememberNavigationEventDispatcherOwner", "Landroidx/navigationevent/NavigationEventDispatcherOwner;", "enabled", "", "parent", "(ZLandroidx/navigationevent/NavigationEventDispatcherOwner;Landroidx/compose/runtime/Composer;II)Landroidx/navigationevent/NavigationEventDispatcherOwner;", "navigationevent-compose"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RememberNavigationEventDispatcherOwnerKt {
    public static final NavigationEventDispatcherOwner rememberNavigationEventDispatcherOwner(boolean z, NavigationEventDispatcherOwner navigationEventDispatcherOwner, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -336636597, "C(rememberNavigationEventDispatcherOwner)N(enabled,parent)61@3115L7,67@3406L325,76@3761L39,76@3737L63,79@3907L43,79@3873L77,81@3963L124:RememberNavigationEventDispatcherOwner.kt#wc8b4r");
        boolean z2 = true;
        if ((i2 & 1) != 0) {
            z = true;
        }
        if ((i2 & 2) != 0 && (navigationEventDispatcherOwner = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composer, 6)) == null) {
            throw new IllegalStateException("No NavigationEventDispatcherOwner provided in LocalNavigationEventDispatcherOwner. If you intended to create a root dispatcher, explicitly pass null as the parent.".toString());
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-336636597, i, -1, "androidx.navigationevent.compose.rememberNavigationEventDispatcherOwner (RememberNavigationEventDispatcherOwner.kt:65)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1385206640, "CC(remember):RememberNavigationEventDispatcherOwner.kt#9igjgp");
        boolean zChanged = composer.changed(navigationEventDispatcherOwner);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            if (navigationEventDispatcherOwner != null) {
                objRememberedValue = new NavigationEventDispatcher(navigationEventDispatcherOwner.getNavigationEventDispatcher());
            } else {
                objRememberedValue = new NavigationEventDispatcher();
            }
            composer.updateRememberedValue(objRememberedValue);
        }
        final NavigationEventDispatcher navigationEventDispatcher = (NavigationEventDispatcher) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        Boolean boolValueOf = Boolean.valueOf(z);
        ComposerKt.sourceInformationMarkerStart(composer, -1385195566, "CC(remember):RememberNavigationEventDispatcherOwner.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navigationEventDispatcher);
        int i3 = i & 14;
        if (((i3 ^ 6) <= 4 || !composer.changed(z)) && (i & 6) != 4) {
            z2 = false;
        }
        boolean z3 = zChangedInstance | z2;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = (Function2) new RememberNavigationEventDispatcherOwnerKt$rememberNavigationEventDispatcherOwner$2$1(navigationEventDispatcher, z, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composer, i3);
        ComposerKt.sourceInformationMarkerStart(composer, -1385190890, "CC(remember):RememberNavigationEventDispatcherOwner.kt#9igjgp");
        boolean zChangedInstance2 = composer.changedInstance(navigationEventDispatcher);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new Function1() { // from class: androidx.navigationevent.compose.RememberNavigationEventDispatcherOwnerKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RememberNavigationEventDispatcherOwnerKt.rememberNavigationEventDispatcherOwner$lambda$5$lambda$4(navigationEventDispatcher, (DisposableEffectScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.DisposableEffect(navigationEventDispatcher, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composer, 0);
        ComposerKt.sourceInformationMarkerStart(composer, -1385189017, "CC(remember):RememberNavigationEventDispatcherOwner.kt#9igjgp");
        boolean zChanged2 = composer.changed(navigationEventDispatcher);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new ComposeNavigationEventDispatcherOwner(navigationEventDispatcher);
            composer.updateRememberedValue(objRememberedValue4);
        }
        ComposeNavigationEventDispatcherOwner composeNavigationEventDispatcherOwner = (ComposeNavigationEventDispatcherOwner) objRememberedValue4;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return composeNavigationEventDispatcherOwner;
    }

    static final DisposableEffectResult rememberNavigationEventDispatcherOwner$lambda$5$lambda$4(final NavigationEventDispatcher navigationEventDispatcher, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.navigationevent.compose.RememberNavigationEventDispatcherOwnerKt$rememberNavigationEventDispatcherOwner$lambda$5$lambda$4$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                navigationEventDispatcher.dispose();
            }
        };
    }
}
