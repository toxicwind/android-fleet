package androidx.activity.compose;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ActivityResultRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001aM\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"rememberLauncherForActivityResult", "Landroidx/activity/compose/ManagedActivityResultLauncher;", "I", "O", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "onResult", "Lkotlin/Function1;", "", "(Landroidx/activity/result/contract/ActivityResultContract;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/activity/compose/ManagedActivityResultLauncher;", "activity-compose"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ActivityResultRegistryKt {
    public static final <I, O> ManagedActivityResultLauncher<I, O> rememberLauncherForActivityResult(final ActivityResultContract<I, O> activityResultContract, Function1<? super O, Unit> function1, Composer composer, int i) {
        final ActivityResultRegistry activityResultRegistry;
        ComposerKt.sourceInformationMarkerStart(composer, -1408504823, "C(rememberLauncherForActivityResult)N(contract,onResult)84@3542L30,85@3599L30,89@3785L32,89@3768L49,92@3906L7,96@4089L46,97@4163L73,101@4419L176,101@4363L232:ActivityResultRegistry.kt#q1dkbc");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1408504823, i, -1, "androidx.activity.compose.rememberLauncherForActivityResult (ActivityResultRegistry.kt:82)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(activityResultContract, composer, i & 14);
        final State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function1, composer, (i >> 3) & 14);
        Object[] objArr = new Object[0];
        ComposerKt.sourceInformationMarkerStart(composer, -658420919, "CC(remember):ActivityResultRegistry.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: androidx.activity.compose.ActivityResultRegistryKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ActivityResultRegistryKt.rememberLauncherForActivityResult$lambda$0$0();
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        final String str = (String) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue, composer, 48);
        ActivityResultRegistryOwner current = LocalActivityResultRegistryOwner.INSTANCE.getCurrent(composer, 6);
        if (current == null) {
            throw new IllegalStateException("No ActivityResultRegistryOwner was provided via LocalActivityResultRegistryOwner".toString());
        }
        ActivityResultRegistry activityResultRegistry2 = current.getActivityResultRegistry();
        ComposerKt.sourceInformationMarkerStart(composer, -658411177, "CC(remember):ActivityResultRegistry.kt#9igjgp");
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new ActivityResultLauncherHolder();
            composer.updateRememberedValue(objRememberedValue2);
        }
        final ActivityResultLauncherHolder activityResultLauncherHolder = (ActivityResultLauncherHolder) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -658408782, "CC(remember):ActivityResultRegistry.kt#9igjgp");
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new ManagedActivityResultLauncher(activityResultLauncherHolder, stateRememberUpdatedState);
            composer.updateRememberedValue(objRememberedValue3);
        }
        ManagedActivityResultLauncher<I, O> managedActivityResultLauncher = (ManagedActivityResultLauncher) objRememberedValue3;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -658400487, "CC(remember):ActivityResultRegistry.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(activityResultLauncherHolder) | composer.changedInstance(activityResultRegistry2) | composer.changed(str) | composer.changedInstance(activityResultContract) | composer.changed(stateRememberUpdatedState2);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            activityResultRegistry = activityResultRegistry2;
            Object obj = new Function1() { // from class: androidx.activity.compose.ActivityResultRegistryKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ActivityResultRegistryKt.rememberLauncherForActivityResult$lambda$4$0(activityResultLauncherHolder, activityResultRegistry, str, activityResultContract, stateRememberUpdatedState2, (DisposableEffectScope) obj2);
                }
            };
            str = str;
            composer.updateRememberedValue(obj);
            objRememberedValue4 = obj;
        } else {
            activityResultRegistry = activityResultRegistry2;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        ActivityResultRegistry activityResultRegistry3 = activityResultRegistry;
        EffectsKt.DisposableEffect(activityResultRegistry3, str, activityResultContract, (Function1) objRememberedValue4, composer, (i << 6) & 896);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return managedActivityResultLauncher;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String rememberLauncherForActivityResult$lambda$0$0() {
        return UUID.randomUUID().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult rememberLauncherForActivityResult$lambda$4$0(final ActivityResultLauncherHolder activityResultLauncherHolder, ActivityResultRegistry activityResultRegistry, String str, ActivityResultContract activityResultContract, final State state, DisposableEffectScope disposableEffectScope) {
        activityResultLauncherHolder.setLauncher(activityResultRegistry.register(str, activityResultContract, new ActivityResultCallback() { // from class: androidx.activity.compose.ActivityResultRegistryKt$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ActivityResultRegistryKt.rememberLauncherForActivityResult$lambda$4$0$0(state, obj);
            }
        }));
        return new DisposableEffectResult() { // from class: androidx.activity.compose.ActivityResultRegistryKt$rememberLauncherForActivityResult$lambda$4$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                activityResultLauncherHolder.unregister();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void rememberLauncherForActivityResult$lambda$4$0$0(State state, Object obj) {
        ((Function1) state.getValue()).invoke(obj);
    }
}
