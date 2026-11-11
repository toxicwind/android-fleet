package androidx.media3.cast;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.mediarouter.app.MediaRouteChooserDialog;
import androidx.mediarouter.app.MediaRouteControllerDialog;
import androidx.mediarouter.app.SystemOutputSwitcherDialogController;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.MediaRouterParams;
import androidx.mediarouter.media.MediaTransferReceiver;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MediaRouteButton.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\u001f\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003¢\u0006\u0002\u0010\t\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\u0006H\u0002\u001a\u001f\u0010\f\u001a\u00020\u0001*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003¢\u0006\u0002\u0010\t\u001a\u001f\u0010\r\u001a\u00020\u0001*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003¢\u0006\u0002\u0010\t\u001a+\u0010\u000e\u001a\u00020\u00012\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0003¢\u0006\u0002\u0010\u0013\"&\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006\u0016²\u0006\n\u0010\u0017\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\u0012\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u0019X\u008a\u008e\u0002"}, d2 = {"MediaRouteButton", "", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "MediaRouteDialog", "Landroidx/media3/cast/MediaRouteButtonState;", "onDismissRequest", "Lkotlin/Function0;", "(Landroidx/media3/cast/MediaRouteButtonState;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "isMediaTransferEnabled", "", "MediaRouteChooserDialog", "MediaRouteControllerDialog", "MediaRouteButtonContainer", "content", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "mediaRouteButtonIcon", "Lkotlin/jvm/functions/Function3;", "lib-cast_release", "showDialog", "selector", "Landroidx/mediarouter/media/MediaRouteSelector;", "kotlin.jvm.PlatformType"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class MediaRouteButtonKt {
    private static final Function3<MediaRouteButtonState, Composer, Integer, Unit> mediaRouteButtonIcon = ComposableSingletons$MediaRouteButtonKt.INSTANCE.m7369getLambda1$lib_cast_release();

    static final Unit MediaRouteButton$lambda$0(Modifier modifier, int i, int i2, Composer composer, int i3) {
        MediaRouteButton(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MediaRouteButtonContainer$lambda$17(Function3 function3, int i, Composer composer, int i2) {
        MediaRouteButtonContainer(function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MediaRouteChooserDialog$lambda$7(MediaRouteButtonState mediaRouteButtonState, Function0 function0, int i, Composer composer, int i2) {
        MediaRouteChooserDialog(mediaRouteButtonState, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MediaRouteControllerDialog$lambda$12(MediaRouteButtonState mediaRouteButtonState, Function0 function0, int i, Composer composer, int i2) {
        MediaRouteControllerDialog(mediaRouteButtonState, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MediaRouteDialog$lambda$1(MediaRouteButtonState mediaRouteButtonState, Function0 function0, int i, Composer composer, int i2) {
        MediaRouteDialog(mediaRouteButtonState, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MediaRouteDialog$lambda$2(MediaRouteButtonState mediaRouteButtonState, Function0 function0, int i, Composer composer, int i2) {
        MediaRouteDialog(mediaRouteButtonState, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.media3.cast.MediaRouteButtonKt$MediaRouteButton$1, reason: invalid class name */
    /* JADX INFO: compiled from: MediaRouteButton.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function3<MediaRouteButtonState, Composer, Integer, Unit> {
        final /* synthetic */ Modifier $modifier;

        AnonymousClass1(Modifier modifier) {
            this.$modifier = modifier;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(MediaRouteButtonState mediaRouteButtonState, Composer composer, Integer num) {
            invoke(mediaRouteButtonState, composer, num.intValue());
            return Unit.INSTANCE;
        }

        private static final boolean invoke$lambda$1(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void invoke$lambda$2(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        public final void invoke(final MediaRouteButtonState MediaRouteButtonContainer, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(MediaRouteButtonContainer, "$this$MediaRouteButtonContainer");
            ComposerKt.sourceInformation(composer, "C67@2693L34,68@2753L21,68@2786L26,68@2732L80,70@2858L22,70@2841L39:MediaRouteButton.kt#rixhmv");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-286638507, i, -1, "androidx.media3.cast.MediaRouteButton.<anonymous> (MediaRouteButton.kt:67)");
            }
            composer.startReplaceGroup(209185037);
            ComposerKt.sourceInformation(composer, "CC(remember):MediaRouteButton.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            composer.endReplaceGroup();
            composer.startReplaceGroup(209186944);
            ComposerKt.sourceInformation(composer, "CC(remember):MediaRouteButton.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: androidx.media3.cast.MediaRouteButtonKt$MediaRouteButton$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MediaRouteButtonKt.AnonymousClass1.invoke$lambda$4$lambda$3(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceGroup();
            IconButtonKt.IconButton((Function0) objRememberedValue2, this.$modifier, false, null, null, ComposableLambdaKt.rememberComposableLambda(-2720718, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.media3.cast.MediaRouteButtonKt.MediaRouteButton.1.2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i2) {
                    ComposerKt.sourceInformation(composer2, "C68@2788L22:MediaRouteButton.kt#rixhmv");
                    if ((i2 & 3) == 2 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2720718, i2, -1, "androidx.media3.cast.MediaRouteButton.<anonymous>.<anonymous> (MediaRouteButton.kt:68)");
                    }
                    MediaRouteButtonKt.mediaRouteButtonIcon.invoke(MediaRouteButtonContainer, composer2, 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composer, 54), composer, 196614, 28);
            if (invoke$lambda$1(mutableState)) {
                composer.startReplaceGroup(209190305);
                ComposerKt.sourceInformation(composer, "CC(remember):MediaRouteButton.kt#9igjgp");
                Object objRememberedValue3 = composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: androidx.media3.cast.MediaRouteButtonKt$MediaRouteButton$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MediaRouteButtonKt.AnonymousClass1.invoke$lambda$6$lambda$5(mutableState);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue3);
                }
                composer.endReplaceGroup();
                MediaRouteButtonKt.MediaRouteDialog(MediaRouteButtonContainer, (Function0) objRememberedValue3, composer, (i & 14) | 48);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        static final Unit invoke$lambda$4$lambda$3(MutableState mutableState) {
            invoke$lambda$2(mutableState, true);
            return Unit.INSTANCE;
        }

        static final Unit invoke$lambda$6$lambda$5(MutableState mutableState) {
            invoke$lambda$2(mutableState, false);
            return Unit.INSTANCE;
        }
    }

    public static final void MediaRouteButton(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1866179016);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MediaRouteButton)66@2669L221,66@2641L249:MediaRouteButton.kt#rixhmv");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i3 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (i4 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1866179016, i3, -1, "androidx.media3.cast.MediaRouteButton (MediaRouteButton.kt:65)");
            }
            MediaRouteButtonContainer(ComposableLambdaKt.rememberComposableLambda(-286638507, true, new AnonymousClass1(modifier), composerStartRestartGroup, 54), composerStartRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.media3.cast.MediaRouteButtonKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MediaRouteButtonKt.MediaRouteButton$lambda$0(modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MediaRouteDialog(final MediaRouteButtonState mediaRouteButtonState, final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        MediaRouterParams routerParams;
        Composer composerStartRestartGroup = composer.startRestartGroup(1241576675);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MediaRouteDialog):MediaRouteButton.kt#rixhmv");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(mediaRouteButtonState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1241576675, i2, -1, "androidx.media3.cast.MediaRouteDialog (MediaRouteButton.kt:76)");
            }
            MediaRouter mediaRouter = mediaRouteButtonState.getMediaRouter();
            boolean z = false;
            if (((mediaRouter == null || (routerParams = mediaRouter.getRouterParams()) == null) ? false : routerParams.isOutputSwitcherEnabled()) && isMediaTransferEnabled(mediaRouteButtonState)) {
                z = true;
            }
            if (z && SystemOutputSwitcherDialogController.showDialog(mediaRouteButtonState.getContext())) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.media3.cast.MediaRouteButtonKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return MediaRouteButtonKt.MediaRouteDialog$lambda$1(mediaRouteButtonState, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            if (mediaRouteButtonState.isConnectedToRemote()) {
                composerStartRestartGroup.startReplaceGroup(-1235814006);
                ComposerKt.sourceInformation(composerStartRestartGroup, "85@3369L44");
                MediaRouteControllerDialog(mediaRouteButtonState, function0, composerStartRestartGroup, i2 & 126);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1235754579);
                ComposerKt.sourceInformation(composerStartRestartGroup, "87@3429L41");
                MediaRouteChooserDialog(mediaRouteButtonState, function0, composerStartRestartGroup, i2 & 126);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: androidx.media3.cast.MediaRouteButtonKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MediaRouteButtonKt.MediaRouteDialog$lambda$2(mediaRouteButtonState, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean isMediaTransferEnabled(MediaRouteButtonState mediaRouteButtonState) {
        if (Build.VERSION.SDK_INT < 30) {
            return false;
        }
        Intent intent = new Intent(mediaRouteButtonState.getContext(), (Class<?>) MediaTransferReceiver.class);
        intent.setPackage(mediaRouteButtonState.getContext().getPackageName());
        List<ResolveInfo> listQueryBroadcastReceivers = mediaRouteButtonState.getContext().getPackageManager().queryBroadcastReceivers(intent, 0);
        Intrinsics.checkNotNullExpressionValue(listQueryBroadcastReceivers, "queryBroadcastReceivers(...)");
        boolean zIsEmpty = listQueryBroadcastReceivers.isEmpty();
        MediaRouter mediaRouter = mediaRouteButtonState.getMediaRouter();
        MediaRouterParams routerParams = mediaRouter != null ? mediaRouter.getRouterParams() : null;
        return !zIsEmpty && (routerParams == null || routerParams.isMediaTransferReceiverEnabled());
    }

    private static final void MediaRouteChooserDialog(final MediaRouteButtonState mediaRouteButtonState, final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1597601814);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MediaRouteChooserDialog)107@4186L223,107@4163L246:MediaRouteButton.kt#rixhmv");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(mediaRouteButtonState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1597601814, i2, -1, "androidx.media3.cast.MediaRouteChooserDialog (MediaRouteButton.kt:106)");
            }
            Unit unit = Unit.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(707081960);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):MediaRouteButton.kt#9igjgp");
            boolean zChangedInstance = ((i2 & 112) == 32) | composerStartRestartGroup.changedInstance(mediaRouteButtonState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.media3.cast.MediaRouteButtonKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MediaRouteButtonKt.MediaRouteChooserDialog$lambda$6$lambda$5(mediaRouteButtonState, function0, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.DisposableEffect(unit, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue, composerStartRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.media3.cast.MediaRouteButtonKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MediaRouteButtonKt.MediaRouteChooserDialog$lambda$7(mediaRouteButtonState, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final DisposableEffectResult MediaRouteChooserDialog$lambda$6$lambda$5(MediaRouteButtonState mediaRouteButtonState, final Function0 function0, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final MediaRouteChooserDialog mediaRouteChooserDialog = new MediaRouteChooserDialog(mediaRouteButtonState.getContext(), R.style.AppThemeDialog);
        mediaRouteChooserDialog.setRouteSelector(mediaRouteButtonState.getSelector());
        mediaRouteChooserDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: androidx.media3.cast.MediaRouteButtonKt$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                function0.invoke();
            }
        });
        mediaRouteChooserDialog.show();
        return new DisposableEffectResult() { // from class: androidx.media3.cast.MediaRouteButtonKt$MediaRouteChooserDialog$lambda$6$lambda$5$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                mediaRouteChooserDialog.dismiss();
            }
        };
    }

    private static final void MediaRouteControllerDialog(final MediaRouteButtonState mediaRouteButtonState, final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(992345703);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MediaRouteControllerDialog)118@4543L190,118@4520L213:MediaRouteButton.kt#rixhmv");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(mediaRouteButtonState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(992345703, i2, -1, "androidx.media3.cast.MediaRouteControllerDialog (MediaRouteButton.kt:117)");
            }
            Unit unit = Unit.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(-1235551044);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):MediaRouteButton.kt#9igjgp");
            boolean zChangedInstance = ((i2 & 112) == 32) | composerStartRestartGroup.changedInstance(mediaRouteButtonState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.media3.cast.MediaRouteButtonKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MediaRouteButtonKt.MediaRouteControllerDialog$lambda$11$lambda$10(mediaRouteButtonState, function0, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.DisposableEffect(unit, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue, composerStartRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.media3.cast.MediaRouteButtonKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MediaRouteButtonKt.MediaRouteControllerDialog$lambda$12(mediaRouteButtonState, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final DisposableEffectResult MediaRouteControllerDialog$lambda$11$lambda$10(MediaRouteButtonState mediaRouteButtonState, final Function0 function0, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final MediaRouteControllerDialog mediaRouteControllerDialog = new MediaRouteControllerDialog(mediaRouteButtonState.getContext(), R.style.AppThemeDialog);
        mediaRouteControllerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: androidx.media3.cast.MediaRouteButtonKt$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                function0.invoke();
            }
        });
        mediaRouteControllerDialog.show();
        return new DisposableEffectResult() { // from class: androidx.media3.cast.MediaRouteButtonKt$MediaRouteControllerDialog$lambda$11$lambda$10$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                mediaRouteControllerDialog.dismiss();
            }
        };
    }

    private static final void MediaRouteButtonContainer(final Function3<? super MediaRouteButtonState, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1306513474);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MediaRouteButtonContainer)133@5016L7,134@5042L53,135@5122L354,135@5098L378,146@5508L48,146@5557L9:MediaRouteButton.kt#rixhmv");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function3) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1306513474, i2, -1, "androidx.media3.cast.MediaRouteButtonContainer (MediaRouteButton.kt:132)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Context context = (Context) objConsume;
            composerStartRestartGroup.startReplaceGroup(90930122);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):MediaRouteButton.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MediaRouteSelector.EMPTY, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(90932983);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):MediaRouteButton.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(context);
            MediaRouteButtonKt$MediaRouteButtonContainer$1$1 mediaRouteButtonKt$MediaRouteButtonContainer$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || mediaRouteButtonKt$MediaRouteButtonContainer$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                mediaRouteButtonKt$MediaRouteButtonContainer$1$1RememberedValue = new MediaRouteButtonKt$MediaRouteButtonContainer$1$1(context, mutableState, null);
                composerStartRestartGroup.updateRememberedValue(mediaRouteButtonKt$MediaRouteButtonContainer$1$1RememberedValue);
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.LaunchedEffect(context, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) mediaRouteButtonKt$MediaRouteButtonContainer$1$1RememberedValue, composerStartRestartGroup, 0);
            if (!MediaRouteButtonContainer$lambda$14(mutableState).isEmpty()) {
                MediaRouteSelector mediaRouteSelectorMediaRouteButtonContainer$lambda$14 = MediaRouteButtonContainer$lambda$14(mutableState);
                Intrinsics.checkNotNullExpressionValue(mediaRouteSelectorMediaRouteButtonContainer$lambda$14, "MediaRouteButtonContainer$lambda$14(...)");
                function3.invoke(MediaRouteButtonStateKt.rememberMediaRouteButtonState(context, mediaRouteSelectorMediaRouteButtonContainer$lambda$14, composerStartRestartGroup, 0), composerStartRestartGroup, Integer.valueOf((i2 << 3) & 112));
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.media3.cast.MediaRouteButtonKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MediaRouteButtonKt.MediaRouteButtonContainer$lambda$17(function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final MediaRouteSelector MediaRouteButtonContainer$lambda$14(MutableState<MediaRouteSelector> mutableState) {
        return mutableState.getValue();
    }
}
