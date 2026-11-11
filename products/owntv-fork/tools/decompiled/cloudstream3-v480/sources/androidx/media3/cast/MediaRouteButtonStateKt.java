package androidx.media3.cast;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.mediarouter.media.MediaRouteSelector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MediaRouteButtonState.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"rememberMediaRouteButtonState", "Landroidx/media3/cast/MediaRouteButtonState;", "context", "Landroid/content/Context;", "selector", "Landroidx/mediarouter/media/MediaRouteSelector;", "(Landroid/content/Context;Landroidx/mediarouter/media/MediaRouteSelector;Landroidx/compose/runtime/Composer;I)Landroidx/media3/cast/MediaRouteButtonState;", "lib-cast_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class MediaRouteButtonStateKt {
    public static final MediaRouteButtonState rememberMediaRouteButtonState(Context context, MediaRouteSelector selector, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(selector, "selector");
        composer.startReplaceGroup(775785389);
        ComposerKt.sourceInformation(composer, "C(rememberMediaRouteButtonState)46@1802L72,47@1915L35,47@1877L73:MediaRouteButtonState.kt#rixhmv");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(775785389, i, -1, "androidx.media3.cast.rememberMediaRouteButtonState (MediaRouteButtonState.kt:44)");
        }
        composer.startReplaceGroup(-1119120192);
        ComposerKt.sourceInformation(composer, "CC(remember):MediaRouteButtonState.kt#9igjgp");
        boolean zChanged = composer.changed(context) | composer.changed(selector);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new MediaRouteButtonState(context, selector);
            composer.updateRememberedValue(objRememberedValue);
        }
        MediaRouteButtonState mediaRouteButtonState = (MediaRouteButtonState) objRememberedValue;
        composer.endReplaceGroup();
        composer.startReplaceGroup(-1119116613);
        ComposerKt.sourceInformation(composer, "CC(remember):MediaRouteButtonState.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(mediaRouteButtonState);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = (Function2) new MediaRouteButtonStateKt$rememberMediaRouteButtonState$1$1(mediaRouteButtonState, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceGroup();
        EffectsKt.LaunchedEffect(mediaRouteButtonState, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return mediaRouteButtonState;
    }
}
