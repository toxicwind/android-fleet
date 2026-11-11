package com.lagradost.cloudstream3.ui.download;

import android.content.Context;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentChildDownloadsBinding;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.result.LinearListLayoutKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.BackPressedCallbackHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadManagerKt;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: DownloadChildFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0016J\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/DownloadChildFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentChildDownloadsBinding;", "<init>", "()V", "downloadViewModel", "Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel;", "getDownloadViewModel", "()Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel;", "downloadViewModel$delegate", "Lkotlin/Lazy;", "onDestroyView", "", "fixLayout", "view", "Landroid/view/View;", "onBindingCreated", "binding", "updateDeleteButton", PeertubeParsingHelper.COUNT_KEY, "", "selectedBytes", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadChildFragment extends BaseFragment<FragmentChildDownloadsBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: downloadViewModel$delegate, reason: from kotlin metadata */
    private final Lazy downloadViewModel;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadChildFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentChildDownloadsBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentChildDownloadsBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentChildDownloadsBinding;", 0);
        }

        public final FragmentChildDownloadsBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentChildDownloadsBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentChildDownloadsBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public DownloadChildFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        final DownloadChildFragment downloadChildFragment = this;
        final Function0 function0 = null;
        this.downloadViewModel = FragmentViewModelLazyKt.createViewModelLazy(downloadChildFragment, Reflection.getOrCreateKotlinClass(DownloadViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return downloadChildFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? downloadChildFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return downloadChildFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
    }

    private final DownloadViewModel getDownloadViewModel() {
        return (DownloadViewModel) this.downloadViewModel.getValue();
    }

    /* JADX INFO: compiled from: DownloadChildFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/DownloadChildFragment$Companion;", "", "<init>", "()V", "newInstance", "Landroid/os/Bundle;", "headerName", "", "folder", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Bundle newInstance(String headerName, String folder) {
            Intrinsics.checkNotNullParameter(headerName, "headerName");
            Intrinsics.checkNotNullParameter(folder, "folder");
            Bundle bundle = new Bundle();
            bundle.putString("folder", folder);
            bundle.putString("name", headerName);
            return bundle;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity, DownloadManagerKt.DOWNLOAD_CHANNEL_NAME);
        }
        getDownloadViewModel().clearChildren();
        super.onDestroyView();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, Globals.INSTANCE.isLandscape(), Globals.INSTANCE.isLayout(6), false, false, false, 462, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentChildDownloadsBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString("folder") : null;
        Bundle arguments2 = getArguments();
        String string2 = arguments2 != null ? arguments2.getString("name") : null;
        if (string == null) {
            dispatchBackPressed();
            return;
        }
        Context context = getContext();
        if (context != null) {
            getDownloadViewModel().updateChildList(context, string);
        }
        MaterialToolbar materialToolbar = binding.downloadChildToolbar;
        materialToolbar.setTitle(string2);
        if (Globals.INSTANCE.isLayout(5)) {
            materialToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            materialToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.dispatchBackPressed();
                }
            });
        }
        UIHelper.INSTANCE.setAppBarNoScrollFlagsOnTV(materialToolbar);
        UIHelper.INSTANCE.setAppBarNoScrollFlagsOnTV(binding.downloadDeleteAppbar);
        DownloadChildFragment downloadChildFragment = this;
        LifecycleKt.observe(downloadChildFragment, getDownloadViewModel().getChildCards(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadChildFragment.onBindingCreated$lambda$2(this.f$0, binding, (Resource) obj);
            }
        });
        LifecycleKt.observe(downloadChildFragment, getDownloadViewModel().getSelectedBytes(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadChildFragment.onBindingCreated$lambda$3(this.f$0, ((Long) obj).longValue());
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadChildFragment.onBindingCreated$lambda$4$0(this.f$0, view);
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadChildFragment.onBindingCreated$lambda$4$1(this.f$0, view);
            }
        });
        binding.btnToggleAll.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadChildFragment.onBindingCreated$lambda$4$2(this.f$0, view);
            }
        });
        LifecycleKt.observeNullable(downloadChildFragment, getDownloadViewModel().getSelectedItemIds(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadChildFragment.onBindingCreated$lambda$5(binding, this, (Set) obj);
            }
        });
        DownloadAdapter downloadAdapter = new DownloadAdapter(new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadChildFragment.onBindingCreated$lambda$6((DownloadHeaderClickEvent) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadChildFragment.onBindingCreated$lambda$7(this.f$0, (DownloadClickEvent) obj);
            }
        }, new Function2() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return DownloadChildFragment.onBindingCreated$lambda$8(this.f$0, ((Integer) obj).intValue(), ((Boolean) obj2).booleanValue());
            }
        });
        RecyclerView recyclerView = binding.downloadChildList;
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setAdapter(downloadAdapter);
        LinearListLayoutKt.setLinearListLayout$default(recyclerView, false, 0, -2, 0, -2, 10, null);
    }

    static final Unit onBindingCreated$lambda$2(DownloadChildFragment downloadChildFragment, FragmentChildDownloadsBinding fragmentChildDownloadsBinding, Resource cards) {
        Intrinsics.checkNotNullParameter(cards, "cards");
        if (cards instanceof Resource.Success) {
            Resource.Success success = (Resource.Success) cards;
            if (((List) success.getValue()).isEmpty()) {
                downloadChildFragment.dispatchBackPressed();
            }
            RecyclerView.Adapter adapter = fragmentChildDownloadsBinding.downloadChildList.getAdapter();
            DownloadAdapter downloadAdapter = adapter instanceof DownloadAdapter ? (DownloadAdapter) adapter : null;
            if (downloadAdapter != null) {
                BaseAdapter.submitList$default(downloadAdapter, (Collection) success.getValue(), null, 2, null);
            }
        } else {
            RecyclerView.Adapter adapter2 = fragmentChildDownloadsBinding.downloadChildList.getAdapter();
            DownloadAdapter downloadAdapter2 = adapter2 instanceof DownloadAdapter ? (DownloadAdapter) adapter2 : null;
            if (downloadAdapter2 != null) {
                BaseAdapter.submitList$default(downloadAdapter2, null, null, 2, null);
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$3(DownloadChildFragment downloadChildFragment, long j) {
        Set<Integer> value = downloadChildFragment.getDownloadViewModel().getSelectedItemIds().getValue();
        downloadChildFragment.updateDeleteButton(value != null ? value.size() : 0, j);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$4$0(DownloadChildFragment downloadChildFragment, View view) {
        DownloadViewModel downloadViewModel = downloadChildFragment.getDownloadViewModel();
        Context context = view.getContext();
        if (context == null) {
            return;
        }
        downloadViewModel.handleMultiDelete(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$4$1(DownloadChildFragment downloadChildFragment, View view) {
        downloadChildFragment.getDownloadViewModel().cancelSelection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$4$2(DownloadChildFragment downloadChildFragment, View view) {
        if (downloadChildFragment.getDownloadViewModel().isAllChildrenSelected()) {
            downloadChildFragment.getDownloadViewModel().clearSelectedItems();
        } else {
            downloadChildFragment.getDownloadViewModel().selectAllChildren();
        }
    }

    static final Unit onBindingCreated$lambda$5(FragmentChildDownloadsBinding fragmentChildDownloadsBinding, final DownloadChildFragment downloadChildFragment, Set set) {
        boolean z = set != null;
        RecyclerView.Adapter adapter = fragmentChildDownloadsBinding.downloadChildList.getAdapter();
        DownloadAdapter downloadAdapter = adapter instanceof DownloadAdapter ? (DownloadAdapter) adapter : null;
        if (downloadAdapter != null) {
            downloadAdapter.setIsMultiDeleteState(z);
        }
        LinearLayout downloadDeleteAppbar = fragmentChildDownloadsBinding.downloadDeleteAppbar;
        Intrinsics.checkNotNullExpressionValue(downloadDeleteAppbar, "downloadDeleteAppbar");
        downloadDeleteAppbar.setVisibility(z ? 0 : 8);
        MaterialToolbar downloadChildToolbar = fragmentChildDownloadsBinding.downloadChildToolbar;
        Intrinsics.checkNotNullExpressionValue(downloadChildToolbar, "downloadChildToolbar");
        downloadChildToolbar.setVisibility(z ? 8 : 0);
        if (set == null) {
            FragmentActivity activity = downloadChildFragment.getActivity();
            if (activity != null) {
                BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity, DownloadManagerKt.DOWNLOAD_CHANNEL_NAME);
            }
            return Unit.INSTANCE;
        }
        FragmentActivity activity2 = downloadChildFragment.getActivity();
        if (activity2 != null) {
            BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(activity2, DownloadManagerKt.DOWNLOAD_CHANNEL_NAME, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadChildFragment$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DownloadChildFragment.onBindingCreated$lambda$5$0(this.f$0, (BackPressedCallbackHelper.CallbackHelper) obj);
                }
            });
        }
        Set set2 = set;
        int size = set2.size();
        Long value = downloadChildFragment.getDownloadViewModel().getSelectedBytes().getValue();
        downloadChildFragment.updateDeleteButton(size, value != null ? value.longValue() : 0L);
        Button btnDelete = fragmentChildDownloadsBinding.btnDelete;
        Intrinsics.checkNotNullExpressionValue(btnDelete, "btnDelete");
        btnDelete.setVisibility(!set2.isEmpty() ? 0 : 8);
        TextView selectItemsText = fragmentChildDownloadsBinding.selectItemsText;
        Intrinsics.checkNotNullExpressionValue(selectItemsText, "selectItemsText");
        selectItemsText.setVisibility(set.isEmpty() ? 0 : 8);
        if (downloadChildFragment.getDownloadViewModel().isAllChildrenSelected()) {
            fragmentChildDownloadsBinding.btnToggleAll.setText(R.string.deselect_all);
        } else {
            fragmentChildDownloadsBinding.btnToggleAll.setText(R.string.select_all);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$5$0(DownloadChildFragment downloadChildFragment, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        downloadChildFragment.getDownloadViewModel().cancelSelection();
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$6(DownloadHeaderClickEvent it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$7(DownloadChildFragment downloadChildFragment, DownloadClickEvent click) {
        Intrinsics.checkNotNullParameter(click, "click");
        if (click.getAction() == 1) {
            Context context = downloadChildFragment.getContext();
            if (context != null) {
                downloadChildFragment.getDownloadViewModel().handleSingleDelete(context, click.getData().getId());
            }
        } else {
            DownloadButtonSetup.INSTANCE.handleDownloadClick(click);
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$8(DownloadChildFragment downloadChildFragment, int i, boolean z) {
        if (z) {
            downloadChildFragment.getDownloadViewModel().addSelected(i);
        } else {
            downloadChildFragment.getDownloadViewModel().removeSelected(i);
        }
        return Unit.INSTANCE;
    }

    private final void updateDeleteButton(int count, long selectedBytes) {
        Button button;
        String shortFileSize = Formatter.formatShortFileSize(getContext(), selectedBytes);
        FragmentChildDownloadsBinding binding = getBinding();
        if (binding == null || (button = binding.btnDelete) == null) {
            return;
        }
        String string = getString(R.string.delete_format);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(count), shortFileSize}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        button.setText(str);
    }
}
