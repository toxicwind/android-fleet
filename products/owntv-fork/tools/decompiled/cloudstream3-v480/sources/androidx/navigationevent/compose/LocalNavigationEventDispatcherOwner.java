package androidx.navigationevent.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.navigationevent.NavigationEventDispatcherOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: LocalNavigationEventDispatcherOwner.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0086\u0004R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u00068G¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Landroidx/navigationevent/compose/LocalNavigationEventDispatcherOwner;", "", "<init>", "()V", "LocalNavigationEventDispatcherOwner", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/navigationevent/NavigationEventDispatcherOwner;", "current", "getCurrent", "(Landroidx/compose/runtime/Composer;I)Landroidx/navigationevent/NavigationEventDispatcherOwner;", "provides", "Landroidx/compose/runtime/ProvidedValue;", "navigationEventDispatcherOwner", "navigationevent-compose"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LocalNavigationEventDispatcherOwner {
    public static final int $stable = 0;
    public static final LocalNavigationEventDispatcherOwner INSTANCE = new LocalNavigationEventDispatcherOwner();
    private static final ProvidableCompositionLocal<NavigationEventDispatcherOwner> LocalNavigationEventDispatcherOwner = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return LocalNavigationEventDispatcherOwner.LocalNavigationEventDispatcherOwner$lambda$0();
        }
    }, 1, null);

    static final NavigationEventDispatcherOwner LocalNavigationEventDispatcherOwner$lambda$0() {
        return null;
    }

    private LocalNavigationEventDispatcherOwner() {
    }

    public final NavigationEventDispatcherOwner getCurrent(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -942026292, "C(<get-current>)38@1631L7:LocalNavigationEventDispatcherOwner.kt#wc8b4r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-942026292, i, -1, "androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner.<get-current> (LocalNavigationEventDispatcherOwner.kt:38)");
        }
        ProvidableCompositionLocal<NavigationEventDispatcherOwner> providableCompositionLocal = LocalNavigationEventDispatcherOwner;
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd(composer);
        NavigationEventDispatcherOwner navigationEventDispatcherOwnerFindViewTreeNavigationEventDispatcherOwner = (NavigationEventDispatcherOwner) objConsume;
        if (navigationEventDispatcherOwnerFindViewTreeNavigationEventDispatcherOwner != null) {
            composer.startReplaceGroup(950834231);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(950836184);
            ComposerKt.sourceInformation(composer, "39@1658L44");
            navigationEventDispatcherOwnerFindViewTreeNavigationEventDispatcherOwner = LocalNavigationEventDispatcherOwner_androidKt.findViewTreeNavigationEventDispatcherOwner(composer, 0);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return navigationEventDispatcherOwnerFindViewTreeNavigationEventDispatcherOwner;
    }

    public final ProvidedValue<NavigationEventDispatcherOwner> provides(NavigationEventDispatcherOwner navigationEventDispatcherOwner) {
        return LocalNavigationEventDispatcherOwner.provides(navigationEventDispatcherOwner);
    }
}
