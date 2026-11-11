package com.lagradost.cloudstream3.ui.settings.extensions;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.AddRepoInputBinding;
import com.lagradost.cloudstream3.plugins.Repository;
import com.lagradost.cloudstream3.plugins.RepositoryManager;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ExtensionsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/settings/extensions/ExtensionsFragment;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$onBindingCreated$addRepositoryClick$1$2$1", f = "ExtensionsFragment.kt", i = {1, 2, 2, 2, 2, 3, 3, 3, 3}, l = {290, 295, 306, 312}, m = "invokeSuspend", n = {ImagesContract.URL, ImagesContract.URL, "repository", "fixedName", "newRepo", ImagesContract.URL, "repository", "fixedName", "newRepo"}, nl = {291, 298, 307, 313}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"}, v = 2)
final class ExtensionsFragment$onBindingCreated$addRepositoryClick$1$2$1 extends SuspendLambda implements Function3<CoroutineScope, ExtensionsFragment, Continuation<? super Unit>, Object> {
    final /* synthetic */ AddRepoInputBinding $binding;
    final /* synthetic */ AlertDialog $dialog;
    final /* synthetic */ String $name;
    final /* synthetic */ String $urlInput;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ ExtensionsFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ExtensionsFragment$onBindingCreated$addRepositoryClick$1$2$1(String str, String str2, ExtensionsFragment extensionsFragment, AlertDialog alertDialog, AddRepoInputBinding addRepoInputBinding, Continuation<? super ExtensionsFragment$onBindingCreated$addRepositoryClick$1$2$1> continuation) {
        super(3, continuation);
        this.$urlInput = str;
        this.$name = str2;
        this.this$0 = extensionsFragment;
        this.$dialog = alertDialog;
        this.$binding = addRepoInputBinding;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, ExtensionsFragment extensionsFragment, Continuation<? super Unit> continuation) {
        return new ExtensionsFragment$onBindingCreated$addRepositoryClick$1$2$1(this.$urlInput, this.$name, this.this$0, this.$dialog, this.$binding, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0093 A[Catch: all -> 0x0055, TRY_LEAVE, TryCatch #0 {all -> 0x0055, blocks: (B:9:0x0026, B:55:0x0133, B:57:0x0139, B:60:0x0140, B:62:0x0148, B:65:0x015e, B:14:0x0043, B:51:0x00ed, B:17:0x004c, B:35:0x008e, B:37:0x0093, B:40:0x00ad, B:42:0x00b3, B:45:0x00ba, B:47:0x00c1, B:46:0x00bd, B:18:0x0051, B:26:0x006c, B:28:0x0073, B:31:0x007b, B:66:0x016d, B:23:0x005b), top: B:70:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00ad A[Catch: all -> 0x0055, TRY_ENTER, TryCatch #0 {all -> 0x0055, blocks: (B:9:0x0026, B:55:0x0133, B:57:0x0139, B:60:0x0140, B:62:0x0148, B:65:0x015e, B:14:0x0043, B:51:0x00ed, B:17:0x004c, B:35:0x008e, B:37:0x0093, B:40:0x00ad, B:42:0x00b3, B:45:0x00ba, B:47:0x00c1, B:46:0x00bd, B:18:0x0051, B:26:0x006c, B:28:0x0073, B:31:0x007b, B:66:0x016d, B:23:0x005b), top: B:70:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:46:0x00bd A[Catch: all -> 0x0055, TryCatch #0 {all -> 0x0055, blocks: (B:9:0x0026, B:55:0x0133, B:57:0x0139, B:60:0x0140, B:62:0x0148, B:65:0x015e, B:14:0x0043, B:51:0x00ed, B:17:0x004c, B:35:0x008e, B:37:0x0093, B:40:0x00ad, B:42:0x00b3, B:45:0x00ba, B:47:0x00c1, B:46:0x00bd, B:18:0x0051, B:26:0x006c, B:28:0x0073, B:31:0x007b, B:66:0x016d, B:23:0x005b), top: B:70:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:50:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:54:0x0132  */
    /* JADX WARN: Code duplicated, block: B:62:0x0148 A[Catch: all -> 0x0055, TRY_LEAVE, TryCatch #0 {all -> 0x0055, blocks: (B:9:0x0026, B:55:0x0133, B:57:0x0139, B:60:0x0140, B:62:0x0148, B:65:0x015e, B:14:0x0043, B:51:0x00ed, B:17:0x004c, B:35:0x008e, B:37:0x0093, B:40:0x00ad, B:42:0x00b3, B:45:0x00ba, B:47:0x00c1, B:46:0x00bd, B:18:0x0051, B:26:0x006c, B:28:0x0073, B:31:0x007b, B:66:0x016d, B:23:0x005b), top: B:70:0x000c }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Unit unit;
        String str;
        Repository repository;
        String str2;
        String name;
        RepositoryData repositoryData;
        String str3;
        RepositoryData repositoryData2;
        List list;
        FragmentActivity activity;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = RepositoryManager.INSTANCE.parseRepoUrl(this.$urlInput, this);
                if (obj == coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i == 2) {
                    String str4 = (String) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    str = str4;
                    repository = (Repository) obj;
                    if (repository != null) {
                        str2 = this.$name;
                        if (str2 != null || StringsKt.isBlank(str2)) {
                            name = repository.getName();
                        } else {
                            name = this.$name;
                        }
                        repositoryData = new RepositoryData(repository.getIconUrl(), name, str);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(str);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(repository);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(name);
                        this.L$3 = repositoryData;
                        this.label = 3;
                        if (RepositoryManager.INSTANCE.addRepository(repositoryData, this) == coroutine_suspended) {
                            str3 = name;
                            this.this$0.getExtensionViewModel().loadStats();
                            this.this$0.getExtensionViewModel().loadRepositories();
                            UIHelper.INSTANCE.dismissSafe(this.$dialog, this.this$0.getActivity());
                            this.L$0 = SpillingKt.nullOutSpilledVariable(str);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(repository);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                            this.L$3 = repositoryData;
                            this.label = 4;
                            obj = RepositoryManager.INSTANCE.getRepoPlugins(repositoryData, this);
                            if (obj != coroutine_suspended) {
                                repositoryData2 = repositoryData;
                            }
                        }
                        return coroutine_suspended;
                    }
                    CommonActivity.INSTANCE.showToast(R.string.no_repository_found_error, Boxing.boxInt(1));
                    unit = Unit.INSTANCE;
                    UIHelper uIHelper = UIHelper.INSTANCE;
                    MaterialButton applyBtt = this.$binding.applyBtt;
                    Intrinsics.checkNotNullExpressionValue(applyBtt, "applyBtt");
                    uIHelper.hideProgress(applyBtt);
                    return unit;
                }
                if (i == 3) {
                    repositoryData = (RepositoryData) this.L$3;
                    str3 = (String) this.L$2;
                    repository = (Repository) this.L$1;
                    str = (String) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    this.this$0.getExtensionViewModel().loadStats();
                    this.this$0.getExtensionViewModel().loadRepositories();
                    UIHelper.INSTANCE.dismissSafe(this.$dialog, this.this$0.getActivity());
                    this.L$0 = SpillingKt.nullOutSpilledVariable(str);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(repository);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                    this.L$3 = repositoryData;
                    this.label = 4;
                    obj = RepositoryManager.INSTANCE.getRepoPlugins(repositoryData, this);
                    if (obj != coroutine_suspended) {
                        repositoryData2 = repositoryData;
                    }
                    return coroutine_suspended;
                }
                if (i != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                repositoryData2 = (RepositoryData) this.L$3;
                ResultKt.throwOnFailure(obj);
            }
            list = (List) obj;
            if (list != null && !list.isEmpty()) {
                activity = this.this$0.getActivity();
                if (activity != null) {
                    AppContextUtils.INSTANCE.addRepositoryDialog(activity, repositoryData2);
                }
                UIHelper uIHelper2 = UIHelper.INSTANCE;
                MaterialButton applyBtt2 = this.$binding.applyBtt;
                Intrinsics.checkNotNullExpressionValue(applyBtt2, "applyBtt");
                uIHelper2.hideProgress(applyBtt2);
                return Unit.INSTANCE;
            }
            CommonActivity.INSTANCE.showToast(R.string.no_plugins_found_error, Boxing.boxInt(1));
            unit = Unit.INSTANCE;
            UIHelper uIHelper3 = UIHelper.INSTANCE;
            MaterialButton applyBtt3 = this.$binding.applyBtt;
            Intrinsics.checkNotNullExpressionValue(applyBtt3, "applyBtt");
            uIHelper3.hideProgress(applyBtt3);
            return unit;
            String str5 = (String) obj;
            String str6 = str5;
            if (str6 != null && !StringsKt.isBlank(str6)) {
                this.L$0 = str5;
                this.label = 2;
                Object repository2 = RepositoryManager.INSTANCE.parseRepository(str5, this);
                if (repository2 != coroutine_suspended) {
                    str = str5;
                    obj = repository2;
                    repository = (Repository) obj;
                    if (repository != null) {
                        CommonActivity.INSTANCE.showToast(R.string.no_repository_found_error, Boxing.boxInt(1));
                        unit = Unit.INSTANCE;
                    } else {
                        str2 = this.$name;
                        if (str2 != null) {
                            name = repository.getName();
                        } else {
                            name = repository.getName();
                        }
                        repositoryData = new RepositoryData(repository.getIconUrl(), name, str);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(str);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(repository);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(name);
                        this.L$3 = repositoryData;
                        this.label = 3;
                        if (RepositoryManager.INSTANCE.addRepository(repositoryData, this) == coroutine_suspended) {
                            str3 = name;
                            this.this$0.getExtensionViewModel().loadStats();
                            this.this$0.getExtensionViewModel().loadRepositories();
                            UIHelper.INSTANCE.dismissSafe(this.$dialog, this.this$0.getActivity());
                            this.L$0 = SpillingKt.nullOutSpilledVariable(str);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(repository);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                            this.L$3 = repositoryData;
                            this.label = 4;
                            obj = RepositoryManager.INSTANCE.getRepoPlugins(repositoryData, this);
                            if (obj != coroutine_suspended) {
                                repositoryData2 = repositoryData;
                                list = (List) obj;
                                if (list != null) {
                                    activity = this.this$0.getActivity();
                                    if (activity != null) {
                                        AppContextUtils.INSTANCE.addRepositoryDialog(activity, repositoryData2);
                                    }
                                    UIHelper uIHelper4 = UIHelper.INSTANCE;
                                    MaterialButton applyBtt4 = this.$binding.applyBtt;
                                    Intrinsics.checkNotNullExpressionValue(applyBtt4, "applyBtt");
                                    uIHelper4.hideProgress(applyBtt4);
                                    return Unit.INSTANCE;
                                }
                                CommonActivity.INSTANCE.showToast(R.string.no_plugins_found_error, Boxing.boxInt(1));
                                unit = Unit.INSTANCE;
                            }
                        }
                    }
                }
                return coroutine_suspended;
            }
            CommonActivity.INSTANCE.showToast(R.string.error_invalid_data, Boxing.boxInt(0));
            unit = Unit.INSTANCE;
            UIHelper uIHelper5 = UIHelper.INSTANCE;
            MaterialButton applyBtt5 = this.$binding.applyBtt;
            Intrinsics.checkNotNullExpressionValue(applyBtt5, "applyBtt");
            uIHelper5.hideProgress(applyBtt5);
            return unit;
        } catch (Throwable th) {
            UIHelper uIHelper6 = UIHelper.INSTANCE;
            MaterialButton applyBtt6 = this.$binding.applyBtt;
            Intrinsics.checkNotNullExpressionValue(applyBtt6, "applyBtt");
            uIHelper6.hideProgress(applyBtt6);
            throw th;
        }
    }
}
