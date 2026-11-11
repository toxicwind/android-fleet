package com.lagradost.cloudstream3.ui.settings.extensions;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: PluginsViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$Companion", f = "PluginsViewModel.kt", i = {0, 0}, l = {85}, m = "getPlugins", n = {"repository", "canUseCache"}, nl = {86}, s = {"L$0", "Z$0"}, v = 2)
final class PluginsViewModel$Companion$getPlugins$1 extends ContinuationImpl {
    Object L$0;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PluginsViewModel.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PluginsViewModel$Companion$getPlugins$1(PluginsViewModel.Companion companion, Continuation<? super PluginsViewModel$Companion$getPlugins$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getPlugins(null, false, this);
    }
}
