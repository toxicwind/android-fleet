package androidx.media3.cast;

import androidx.compose.material3.IconKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaRouteButton.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$MediaRouteButtonKt {
    public static final ComposableSingletons$MediaRouteButtonKt INSTANCE = new ComposableSingletons$MediaRouteButtonKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3<MediaRouteButtonState, Composer, Integer, Unit> f60lambda1 = ComposableLambdaKt.composableLambdaInstance(1358229378, false, new Function3<MediaRouteButtonState, Composer, Integer, Unit>() { // from class: androidx.media3.cast.ComposableSingletons$MediaRouteButtonKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(MediaRouteButtonState mediaRouteButtonState, Composer composer, Integer num) {
            invoke(mediaRouteButtonState, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(MediaRouteButtonState mediaRouteButtonState, Composer composer, int i) {
            Painter painterPainterResource;
            String strStringResource;
            Intrinsics.checkNotNullParameter(mediaRouteButtonState, "<this>");
            ComposerKt.sourceInformation(composer, "C166@6290L33:MediaRouteButton.kt#rixhmv");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1358229378, i, -1, "androidx.media3.cast.ComposableSingletons$MediaRouteButtonKt.lambda-1.<anonymous> (MediaRouteButton.kt:152)");
            }
            if (mediaRouteButtonState.getConnectionState() == 2) {
                composer.startReplaceGroup(305892694);
                ComposerKt.sourceInformation(composer, "155@5778L56");
                painterPainterResource = PainterResources_androidKt.painterResource(R.drawable.media_route_button_connected, composer, 0);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(305895033);
                ComposerKt.sourceInformation(composer, "156@5851L59");
                painterPainterResource = PainterResources_androidKt.painterResource(R.drawable.media_route_button_disconnected, composer, 0);
                composer.endReplaceGroup();
            }
            Painter painter = painterPainterResource;
            int connectionState = mediaRouteButtonState.getConnectionState();
            if (connectionState == 1) {
                composer.startReplaceGroup(305904596);
                ComposerKt.sourceInformation(composer, "163@6150L54");
                strStringResource = StringResources_androidKt.stringResource(R.string.media_route_button_connecting, composer, 0);
                composer.endReplaceGroup();
            } else if (connectionState == 2) {
                composer.startReplaceGroup(305900979);
                ComposerKt.sourceInformation(composer, "161@6037L53");
                strStringResource = StringResources_androidKt.stringResource(R.string.media_route_button_connected, composer, 0);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(305906870);
                ComposerKt.sourceInformation(composer, "164@6221L56");
                strStringResource = StringResources_androidKt.stringResource(R.string.media_route_button_disconnected, composer, 0);
                composer.endReplaceGroup();
            }
            IconKt.m1867Iconww6aTOc(painter, strStringResource, (Modifier) null, 0L, composer, 0, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$lib_cast_release, reason: not valid java name */
    public final Function3<MediaRouteButtonState, Composer, Integer, Unit> m7369getLambda1$lib_cast_release() {
        return f60lambda1;
    }
}
