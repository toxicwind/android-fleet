package androidx.navigationevent.compose;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.navigationevent.NavigationEventDispatcherOwner;
import androidx.navigationevent.ViewTreeNavigationEventDispatcherOwner;
import kotlin.Metadata;

/* JADX INFO: compiled from: LocalNavigationEventDispatcherOwner.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000f\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\u0001¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"findViewTreeNavigationEventDispatcherOwner", "Landroidx/navigationevent/NavigationEventDispatcherOwner;", "(Landroidx/compose/runtime/Composer;I)Landroidx/navigationevent/NavigationEventDispatcherOwner;", "navigationevent-compose"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LocalNavigationEventDispatcherOwner_androidKt {
    public static final NavigationEventDispatcherOwner findViewTreeNavigationEventDispatcherOwner(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -334864187, "C(findViewTreeNavigationEventDispatcherOwner)25@1013L7:LocalNavigationEventDispatcherOwner.android.kt#wc8b4r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-334864187, i, -1, "androidx.navigationevent.compose.findViewTreeNavigationEventDispatcherOwner (LocalNavigationEventDispatcherOwner.android.kt:25)");
        }
        ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localView);
        ComposerKt.sourceInformationMarkerEnd(composer);
        NavigationEventDispatcherOwner navigationEventDispatcherOwner = ViewTreeNavigationEventDispatcherOwner.get((View) objConsume);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return navigationEventDispatcherOwner;
    }
}
