package com.lagradost.cloudstream3.ui.download.queue;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentDownloadQueueBinding;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: DownloadQueueFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentDownloadQueueBinding;", "<init>", "()V", "queueViewModel", "Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueViewModel;", "getQueueViewModel", "()Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueViewModel;", "queueViewModel$delegate", "Lkotlin/Lazy;", "onBindingCreated", "", "binding", "fixLayout", "view", "Landroid/view/View;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadQueueFragment extends BaseFragment<FragmentDownloadQueueBinding> {

    /* JADX INFO: renamed from: queueViewModel$delegate, reason: from kotlin metadata */
    private final Lazy queueViewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$1$0$1$1(DialogInterface dialogInterface, int i) {
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadQueueFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentDownloadQueueBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentDownloadQueueBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentDownloadQueueBinding;", 0);
        }

        public final FragmentDownloadQueueBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentDownloadQueueBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentDownloadQueueBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public DownloadQueueFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        final DownloadQueueFragment downloadQueueFragment = this;
        final Function0 function0 = null;
        this.queueViewModel = FragmentViewModelLazyKt.createViewModelLazy(downloadQueueFragment, Reflection.getOrCreateKotlinClass(DownloadQueueViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return downloadQueueFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? downloadQueueFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return downloadQueueFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
    }

    private final DownloadQueueViewModel getQueueViewModel() {
        return (DownloadQueueViewModel) this.queueViewModel.getValue();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentDownloadQueueBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        final DownloadQueueAdapter downloadQueueAdapter = new DownloadQueueAdapter(this);
        Menu menu = binding.downloadQueueToolbar.getMenu();
        final MenuItem menuItemFindItem = menu != null ? menu.findItem(R.id.cancel_all) : null;
        LifecycleKt.observe(this, getQueueViewModel().getChildCards(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadQueueFragment.onBindingCreated$lambda$0(binding, menuItemFindItem, downloadQueueAdapter, (DownloadAdapterQueue) obj);
            }
        });
        final MaterialToolbar materialToolbar = binding.downloadQueueToolbar;
        UiText uiTextTxt = TextUtilKt.txt(R.string.download_queue, new Object[0]);
        Context context = materialToolbar.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        materialToolbar.setTitle(uiTextTxt.asString(context));
        if (Globals.INSTANCE.isLayout(5)) {
            materialToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            materialToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.dispatchBackPressed();
                }
            });
        }
        UIHelper.INSTANCE.setAppBarNoScrollFlagsOnTV(materialToolbar);
        if (menuItemFindItem != null) {
            menuItemFindItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueFragment$$ExternalSyntheticLambda2
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return DownloadQueueFragment.onBindingCreated$lambda$1$0$1(materialToolbar, menuItem);
                }
            });
        }
        binding.downloadQueueList.setAdapter(downloadQueueAdapter);
        new DragAndDropTouchHelper(downloadQueueAdapter).attachToRecyclerView(binding.downloadQueueList);
    }

    static final Unit onBindingCreated$lambda$0(FragmentDownloadQueueBinding fragmentDownloadQueueBinding, MenuItem menuItem, DownloadQueueAdapter downloadQueueAdapter, DownloadAdapterQueue cards) {
        Intrinsics.checkNotNullParameter(cards, "cards");
        boolean z = cards.getQueue().size() + cards.getCurrentDownloads().size() == 0;
        RecyclerView downloadQueueList = fragmentDownloadQueueBinding.downloadQueueList;
        Intrinsics.checkNotNullExpressionValue(downloadQueueList, "downloadQueueList");
        downloadQueueList.setVisibility(z ? 8 : 0);
        TextView textNoQueue = fragmentDownloadQueueBinding.textNoQueue;
        Intrinsics.checkNotNullExpressionValue(textNoQueue, "textNoQueue");
        textNoQueue.setVisibility(z ? 0 : 8);
        if (menuItem != null) {
            menuItem.setVisible(!z);
        }
        downloadQueueAdapter.submitQueue(cards);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$1$0$1(MaterialToolbar materialToolbar, MenuItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        new AlertDialog.Builder(materialToolbar.getContext(), R.style.AlertDialogCustom).setTitle(R.string.cancel_all).setMessage(R.string.cancel_queue_message).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueFragment$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DownloadQueueFragment.onBindingCreated$lambda$1$0$1$0(dialogInterface, i);
            }
        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueFragment$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DownloadQueueFragment.onBindingCreated$lambda$1$0$1$1(dialogInterface, i);
            }
        }).show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$1$0$1$0(DialogInterface dialogInterface, int i) {
        DownloadQueueManager.INSTANCE.removeAllFromQueue();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, Globals.INSTANCE.isLandscape(), Globals.INSTANCE.isLayout(6), false, false, false, 462, null);
    }
}
