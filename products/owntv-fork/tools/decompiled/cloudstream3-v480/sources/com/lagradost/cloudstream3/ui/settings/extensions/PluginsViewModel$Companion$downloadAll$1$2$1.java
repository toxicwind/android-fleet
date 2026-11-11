package com.lagradost.cloudstream3.ui.settings.extensions;

import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.plugins.PluginWrapper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PluginsViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$Companion$downloadAll$1$2$1", f = "PluginsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class PluginsViewModel$Companion$downloadAll$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<PluginWrapper> $list;
    final /* synthetic */ List<PluginWrapper> $plugins;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PluginsViewModel$Companion$downloadAll$1$2$1(List<PluginWrapper> list, List<PluginWrapper> list2, Continuation<? super PluginsViewModel$Companion$downloadAll$1$2$1> continuation) {
        super(2, continuation);
        this.$plugins = list;
        this.$list = list2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PluginsViewModel$Companion$downloadAll$1$2$1(this.$plugins, this.$list, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PluginsViewModel$Companion$downloadAll$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        UiText uiTextTxt;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CommonActivity commonActivity = CommonActivity.INSTANCE;
        if (this.$plugins.isEmpty()) {
            uiTextTxt = TextUtilKt.txt(R.string.no_plugins_found_error, new Object[0]);
        } else if (this.$list.isEmpty()) {
            uiTextTxt = TextUtilKt.txt(R.string.batch_download_nothing_to_download_format, TextUtilKt.txt(R.string.plugin, new Object[0]));
        } else {
            uiTextTxt = TextUtilKt.txt(R.string.batch_download_start_format, Boxing.boxInt(this.$list.size()), TextUtilKt.txt(this.$list.size() == 1 ? R.string.plugin_singular : R.string.plugin, new Object[0]));
        }
        commonActivity.showToast(uiTextTxt, Boxing.boxInt(0));
        return Unit.INSTANCE;
    }
}
