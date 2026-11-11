package com.lagradost.cloudstream3.ui.settings.extensions;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentExtensionsBinding;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExtensionsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepoAdapter;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$onBindingCreated$1$4$1", f = "ExtensionsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class ExtensionsFragment$onBindingCreated$1$4$1 extends SuspendLambda implements Function2<RepoAdapter, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentExtensionsBinding $binding;
    final /* synthetic */ RepositoryData $repo;
    final /* synthetic */ RepoAdapter $this_RepoAdapter;
    final /* synthetic */ RecyclerView $this_apply;
    int label;
    final /* synthetic */ ExtensionsFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ExtensionsFragment$onBindingCreated$1$4$1(RecyclerView recyclerView, FragmentExtensionsBinding fragmentExtensionsBinding, RepoAdapter repoAdapter, RepositoryData repositoryData, ExtensionsFragment extensionsFragment, Continuation<? super ExtensionsFragment$onBindingCreated$1$4$1> continuation) {
        super(2, continuation);
        this.$this_apply = recyclerView;
        this.$binding = fragmentExtensionsBinding;
        this.$this_RepoAdapter = repoAdapter;
        this.$repo = repositoryData;
        this.this$0 = extensionsFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtensionsFragment$onBindingCreated$1$4$1(this.$this_apply, this.$binding, this.$this_RepoAdapter, this.$repo, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(RepoAdapter repoAdapter, Continuation<? super Unit> continuation) {
        return ((ExtensionsFragment$onBindingCreated$1$4$1) create(repoAdapter, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        final Context context = this.$this_apply.getContext();
        if (context == null) {
            context = this.$binding.getRoot().getContext();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final RepoAdapter repoAdapter = this.$this_RepoAdapter;
        final RepositoryData repositoryData = this.$repo;
        final ExtensionsFragment extensionsFragment = this.this$0;
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$onBindingCreated$1$4$1$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ExtensionsFragment$onBindingCreated$1$4$1.invokeSuspend$lambda$0(repoAdapter, context, repositoryData, extensionsFragment, dialogInterface, i);
            }
        };
        AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
        AlertDialog alertDialogShow = builder.setTitle(R.string.delete_repository).setMessage(context.getString(R.string.delete_repository_plugins)).setPositiveButton(R.string.delete, onClickListener).setNegativeButton(R.string.cancel, onClickListener).show();
        Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
        AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
        return Unit.INSTANCE;
    }

    static final void invokeSuspend$lambda$0(RepoAdapter repoAdapter, Context context, RepositoryData repositoryData, ExtensionsFragment extensionsFragment, DialogInterface dialogInterface, int i) {
        if (i != -1) {
            return;
        }
        Coroutines.INSTANCE.ioSafe(repoAdapter, new ExtensionsFragment$onBindingCreated$1$4$1$dialogClickListener$1$1(context, repositoryData, extensionsFragment, null));
    }
}
