package com.lagradost.cloudstream3.ui.settings.extensions;

import com.lagradost.cloudstream3.databinding.FragmentPluginDetailsBinding;
import com.lagradost.cloudstream3.plugins.SitePlugin;
import com.lagradost.cloudstream3.plugins.VotingApi;
import com.lagradost.cloudstream3.utils.Coroutines;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PluginDetailsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/databinding/FragmentPluginDetailsBinding;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginDetailsFragment$onBindingCreated$1$5", f = "PluginDetailsFragment.kt", i = {}, l = {130}, m = "invokeSuspend", n = {}, nl = {133}, s = {}, v = 2)
final class PluginDetailsFragment$onBindingCreated$1$5 extends SuspendLambda implements Function3<CoroutineScope, FragmentPluginDetailsBinding, Continuation<? super Unit>, Object> {
    final /* synthetic */ SitePlugin $metadata;
    Object L$0;
    int label;
    final /* synthetic */ PluginDetailsFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PluginDetailsFragment$onBindingCreated$1$5(SitePlugin sitePlugin, PluginDetailsFragment pluginDetailsFragment, Continuation<? super PluginDetailsFragment$onBindingCreated$1$5> continuation) {
        super(3, continuation);
        this.$metadata = sitePlugin;
        this.this$0 = pluginDetailsFragment;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, FragmentPluginDetailsBinding fragmentPluginDetailsBinding, Continuation<? super Unit> continuation) {
        return new PluginDetailsFragment$onBindingCreated$1$5(this.$metadata, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.PluginDetailsFragment$onBindingCreated$1$5$1, reason: invalid class name */
    /* JADX INFO: compiled from: PluginDetailsFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginDetailsFragment$onBindingCreated$1$5$1", f = "PluginDetailsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Integer, Continuation<? super Unit>, Object> {
        /* synthetic */ int I$0;
        int label;
        final /* synthetic */ PluginDetailsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PluginDetailsFragment pluginDetailsFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = pluginDetailsFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.I$0 = ((Number) obj).intValue();
            return anonymousClass1;
        }

        public final Object invoke(int i, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(Integer.valueOf(i), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Integer num, Continuation<? super Unit> continuation) {
            return invoke(num.intValue(), continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int i = this.I$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.updateVoting(i);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Coroutines coroutines;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Coroutines coroutines2 = Coroutines.INSTANCE;
            this.L$0 = coroutines2;
            this.label = 1;
            Object votes = VotingApi.INSTANCE.getVotes(this.$metadata, this);
            if (votes == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutines = coroutines2;
            obj = votes;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutines = (Coroutines) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        coroutines.main(obj, new AnonymousClass1(this.this$0, null));
        return Unit.INSTANCE;
    }
}
