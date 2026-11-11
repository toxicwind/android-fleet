package com.lagradost.cloudstream3.plugins;

import android.app.Activity;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PluginManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/plugins/PluginManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$3$1", f = "PluginManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$3$1 extends SuspendLambda implements Function2<PluginManager, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ List<String> $updatedPlugins;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$3$1(List<String> list, Activity activity, Continuation<? super PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$3$1> continuation) {
        super(2, continuation);
        this.$updatedPlugins = list;
        this.$activity = activity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$3$1(this.$updatedPlugins, this.$activity, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PluginManager pluginManager, Continuation<? super Unit> continuation) {
        return ((PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$3$1) create(pluginManager, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String string;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean zIsEmpty = this.$updatedPlugins.isEmpty();
        Activity activity = this.$activity;
        if (!zIsEmpty) {
            string = activity.getString(R.string.plugins_updated_manually, new Object[]{Boxing.boxInt(this.$updatedPlugins.size())});
        } else {
            string = activity.getString(R.string.no_plugins_updated_manually);
        }
        Intrinsics.checkNotNull(string);
        CommonActivity.INSTANCE.showToast(string, Boxing.boxInt(1));
        PluginManager.INSTANCE.createNotification(this.$activity, new UiText.StringResource(R.string.plugins_updated_manually, CollectionsKt.listOf(Boxing.boxInt(this.$updatedPlugins.size()))), this.$updatedPlugins);
        return Unit.INSTANCE;
    }
}
