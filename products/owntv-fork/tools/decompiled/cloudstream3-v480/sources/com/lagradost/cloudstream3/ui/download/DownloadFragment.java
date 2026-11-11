package com.lagradost.cloudstream3.ui.download;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentDownloadsBinding;
import com.lagradost.cloudstream3.databinding.StreamInputBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.download.queue.DownloadAdapterQueue;
import com.lagradost.cloudstream3.ui.download.queue.DownloadQueueViewModel;
import com.lagradost.cloudstream3.ui.player.BasicLink;
import com.lagradost.cloudstream3.ui.player.GeneratorPlayer;
import com.lagradost.cloudstream3.ui.player.LinkGenerator;
import com.lagradost.cloudstream3.ui.player.OfflinePlaybackHelper;
import com.lagradost.cloudstream3.ui.result.LinearListLayoutKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.BackPressedCallbackHelper;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadManagerKt;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: DownloadFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0012H\u0016J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0002H\u0016J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0014H\u0002J6\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00142\b\b\u0001\u0010%\u001a\u00020\u001f2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010(\u001a\u00020\u0011H\u0002J\u0010\u0010)\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0002J\u001a\u0010*\u001a\u00020\u00112\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010\u0019\u001a\u00020-H\u0002J\u0010\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u001fH\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001c\u00100\u001a\u0010\u0012\f\u0012\n 3*\u0004\u0018\u0001020201X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/DownloadFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentDownloadsBinding;", "<init>", "()V", "downloadViewModel", "Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel;", "getDownloadViewModel", "()Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel;", "downloadViewModel$delegate", "Lkotlin/Lazy;", "downloadQueueViewModel", "Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueViewModel;", "getDownloadQueueViewModel", "()Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueViewModel;", "downloadQueueViewModel$delegate", "setLayoutWidth", "", "Landroid/view/View;", "weight", "", "onDestroyView", "fixLayout", "view", "onBindingCreated", "binding", "handleItemClick", "click", "Lcom/lagradost/cloudstream3/ui/download/DownloadHeaderClickEvent;", "updateDeleteButton", PeertubeParsingHelper.COUNT_KEY, "", "selectedBytes", "updateStorageInfo", "context", "Landroid/content/Context;", "bytes", "stringRes", "textView", "Landroid/widget/TextView;", "openLocalVideo", "showStreamInputDialog", "activateSwitchOnHls", "text", "", "Lcom/lagradost/cloudstream3/databinding/StreamInputBinding;", "handleScroll", "dy", "videoResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadFragment extends BaseFragment<FragmentDownloadsBinding> {

    /* JADX INFO: renamed from: downloadQueueViewModel$delegate, reason: from kotlin metadata */
    private final Lazy downloadQueueViewModel;

    /* JADX INFO: renamed from: downloadViewModel$delegate, reason: from kotlin metadata */
    private final Lazy downloadViewModel;
    private final ActivityResultLauncher<Intent> videoResultLauncher;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.DownloadFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentDownloadsBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentDownloadsBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentDownloadsBinding;", 0);
        }

        public final FragmentDownloadsBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentDownloadsBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentDownloadsBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public DownloadFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        final DownloadFragment downloadFragment = this;
        final Function0 function0 = null;
        this.downloadViewModel = FragmentViewModelLazyKt.createViewModelLazy(downloadFragment, Reflection.getOrCreateKotlinClass(DownloadViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return downloadFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? downloadFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return downloadFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        this.downloadQueueViewModel = FragmentViewModelLazyKt.createViewModelLazy(downloadFragment, Reflection.getOrCreateKotlinClass(DownloadQueueViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return downloadFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$special$$inlined$activityViewModels$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? downloadFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$special$$inlined$activityViewModels$default$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return downloadFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DownloadFragment.videoResultLauncher$lambda$0(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.videoResultLauncher = activityResultLauncherRegisterForActivityResult;
    }

    private final DownloadViewModel getDownloadViewModel() {
        return (DownloadViewModel) this.downloadViewModel.getValue();
    }

    private final DownloadQueueViewModel getDownloadQueueViewModel() {
        return (DownloadQueueViewModel) this.downloadQueueViewModel.getValue();
    }

    private final void setLayoutWidth(View view, long j) {
        view.setLayoutParams(new LinearLayout.LayoutParams(0, -1, Math.max(j / 1.0E9f, 0.1f)));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity, DownloadManagerKt.DOWNLOAD_CHANNEL_NAME);
        }
        super.onDestroyView();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, Globals.INSTANCE.isLandscape(), Globals.INSTANCE.isLayout(6), false, false, false, 462, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentDownloadsBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        UIHelper.INSTANCE.hideKeyboard(this);
        UIHelper.INSTANCE.setAppBarNoScrollFlagsOnTV(binding.downloadAppbar);
        UIHelper.INSTANCE.setAppBarNoScrollFlagsOnTV(binding.downloadDeleteAppbar);
        DownloadFragment downloadFragment = this;
        LifecycleKt.observe(downloadFragment, getDownloadViewModel().getHeaderCards(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.onBindingCreated$lambda$0(binding, (Resource) obj);
            }
        });
        LifecycleKt.observe(downloadFragment, getDownloadViewModel().getAvailableBytes(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.onBindingCreated$lambda$1(this.f$0, binding, ((Long) obj).longValue());
            }
        });
        LifecycleKt.observe(downloadFragment, getDownloadViewModel().getUsedBytes(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.onBindingCreated$lambda$2(this.f$0, binding, ((Long) obj).longValue());
            }
        });
        LifecycleKt.observe(downloadFragment, getDownloadViewModel().getDownloadBytes(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.onBindingCreated$lambda$3(this.f$0, binding, ((Long) obj).longValue());
            }
        });
        LifecycleKt.observe(downloadFragment, getDownloadQueueViewModel().getChildCards(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.onBindingCreated$lambda$4(binding, (DownloadAdapterQueue) obj);
            }
        });
        LifecycleKt.observe(downloadFragment, getDownloadViewModel().getSelectedBytes(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.onBindingCreated$lambda$5(this.f$0, ((Long) obj).longValue());
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.onBindingCreated$lambda$6$0(this.f$0, view);
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.onBindingCreated$lambda$6$1(this.f$0, view);
            }
        });
        binding.btnToggleAll.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.onBindingCreated$lambda$6$2(this.f$0, view);
            }
        });
        LifecycleKt.observeNullable(downloadFragment, getDownloadViewModel().getSelectedItemIds(), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.onBindingCreated$lambda$7(binding, this, (Set) obj);
            }
        });
        DownloadAdapter downloadAdapter = new DownloadAdapter(new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.onBindingCreated$lambda$8(this.f$0, (DownloadHeaderClickEvent) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.onBindingCreated$lambda$9(this.f$0, (DownloadClickEvent) obj);
            }
        }, new Function2() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return DownloadFragment.onBindingCreated$lambda$10(this.f$0, ((Integer) obj).intValue(), ((Boolean) obj2).booleanValue());
            }
        });
        RecyclerView recyclerView = binding.downloadList;
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setAdapter(downloadAdapter);
        LinearListLayoutKt.setLinearListLayout$default(recyclerView, false, 0, -2, 0, R.id.download_queue_button, 10, null);
        FloatingActionButton floatingActionButton = binding.openLocalVideoButton;
        Intrinsics.checkNotNull(floatingActionButton);
        floatingActionButton.setVisibility(Globals.INSTANCE.isLayout(2) ? 8 : 0);
        floatingActionButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.openLocalVideo();
            }
        });
        ExtendedFloatingActionButton extendedFloatingActionButton = binding.downloadStreamButton;
        Intrinsics.checkNotNull(extendedFloatingActionButton);
        extendedFloatingActionButton.setVisibility(Globals.INSTANCE.isLayout(2) ? 8 : 0);
        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.onBindingCreated$lambda$12$1$0(this.f$0, view);
            }
        });
        binding.downloadQueueButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.onBindingCreated$lambda$12$2(this.f$0, view);
            }
        });
        binding.downloadStreamButtonTv.setFocusableInTouchMode(Globals.INSTANCE.isLayout(2));
        binding.downloadAppbar.setFocusableInTouchMode(Globals.INSTANCE.isLayout(2));
        binding.downloadStreamButtonTv.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.onBindingCreated$lambda$12$3(this.f$0, view);
            }
        });
        LinearLayout steamImageviewHolder = binding.steamImageviewHolder;
        Intrinsics.checkNotNullExpressionValue(steamImageviewHolder, "steamImageviewHolder");
        steamImageviewHolder.setVisibility(Globals.INSTANCE.isLayout(2) ? 0 : 8);
        binding.downloadList.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                this.f$0.handleScroll(i2 - i4);
            }
        });
        Context context = getContext();
        if (context != null) {
            getDownloadViewModel().updateHeaderList(context);
        }
    }

    static final Unit onBindingCreated$lambda$0(FragmentDownloadsBinding fragmentDownloadsBinding, Resource cards) {
        Intrinsics.checkNotNullParameter(cards, "cards");
        if (cards instanceof Resource.Success) {
            RecyclerView.Adapter adapter = fragmentDownloadsBinding.downloadList.getAdapter();
            DownloadAdapter downloadAdapter = adapter instanceof DownloadAdapter ? (DownloadAdapter) adapter : null;
            if (downloadAdapter != null) {
                BaseAdapter.submitList$default(downloadAdapter, (Collection) ((Resource.Success) cards).getValue(), null, 2, null);
            }
            TextView textNoDownloads = fragmentDownloadsBinding.textNoDownloads;
            Intrinsics.checkNotNullExpressionValue(textNoDownloads, "textNoDownloads");
            textNoDownloads.setVisibility(((List) ((Resource.Success) cards).getValue()).isEmpty() ? 0 : 8);
            ShimmerFrameLayout downloadLoading = fragmentDownloadsBinding.downloadLoading;
            Intrinsics.checkNotNullExpressionValue(downloadLoading, "downloadLoading");
            downloadLoading.setVisibility(8);
            RecyclerView downloadList = fragmentDownloadsBinding.downloadList;
            Intrinsics.checkNotNullExpressionValue(downloadList, "downloadList");
            downloadList.setVisibility(0);
        } else if (cards instanceof Resource.Loading) {
            RecyclerView downloadList2 = fragmentDownloadsBinding.downloadList;
            Intrinsics.checkNotNullExpressionValue(downloadList2, "downloadList");
            downloadList2.setVisibility(8);
            ShimmerFrameLayout downloadLoading2 = fragmentDownloadsBinding.downloadLoading;
            Intrinsics.checkNotNullExpressionValue(downloadLoading2, "downloadLoading");
            downloadLoading2.setVisibility(0);
        } else {
            if (!(cards instanceof Resource.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            RecyclerView downloadList3 = fragmentDownloadsBinding.downloadList;
            Intrinsics.checkNotNullExpressionValue(downloadList3, "downloadList");
            downloadList3.setVisibility(0);
            ShimmerFrameLayout downloadLoading3 = fragmentDownloadsBinding.downloadLoading;
            Intrinsics.checkNotNullExpressionValue(downloadLoading3, "downloadLoading");
            downloadLoading3.setVisibility(8);
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$1(DownloadFragment downloadFragment, FragmentDownloadsBinding fragmentDownloadsBinding, long j) {
        Context context = fragmentDownloadsBinding.getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        downloadFragment.updateStorageInfo(context, j, R.string.free_storage, fragmentDownloadsBinding.downloadFreeTxt, fragmentDownloadsBinding.downloadFree);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$2(DownloadFragment downloadFragment, FragmentDownloadsBinding fragmentDownloadsBinding, long j) {
        Context context = fragmentDownloadsBinding.getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        downloadFragment.updateStorageInfo(context, j, R.string.used_storage, fragmentDownloadsBinding.downloadUsedTxt, fragmentDownloadsBinding.downloadUsed);
        boolean z = j > 0;
        if (z) {
            fragmentDownloadsBinding.downloadLoadingBytes.stopShimmer();
        } else {
            fragmentDownloadsBinding.downloadLoadingBytes.startShimmer();
        }
        LinearLayout downloadBytesBar = fragmentDownloadsBinding.downloadBytesBar;
        Intrinsics.checkNotNullExpressionValue(downloadBytesBar, "downloadBytesBar");
        downloadBytesBar.setVisibility(z ? 0 : 8);
        ShimmerFrameLayout downloadLoadingBytes = fragmentDownloadsBinding.downloadLoadingBytes;
        Intrinsics.checkNotNullExpressionValue(downloadLoadingBytes, "downloadLoadingBytes");
        downloadLoadingBytes.setVisibility(z ? 8 : 0);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$3(DownloadFragment downloadFragment, FragmentDownloadsBinding fragmentDownloadsBinding, long j) {
        Context context = fragmentDownloadsBinding.getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        downloadFragment.updateStorageInfo(context, j, R.string.app_storage, fragmentDownloadsBinding.downloadAppTxt, fragmentDownloadsBinding.downloadApp);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$4(FragmentDownloadsBinding fragmentDownloadsBinding, DownloadAdapterQueue cards) {
        String str;
        Intrinsics.checkNotNullParameter(cards, "cards");
        int size = cards.getCurrentDownloads().size() + cards.getQueue().size();
        String string = fragmentDownloadsBinding.getRoot().getContext().getString(R.string.download_queue);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        TextView textView = fragmentDownloadsBinding.downloadQueueText;
        if (size > 0) {
            str = string + " (" + cards.getCurrentDownloads().size() + '/' + size + ')';
        } else {
            str = string;
        }
        textView.setText(str);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$5(DownloadFragment downloadFragment, long j) {
        Set<Integer> value = downloadFragment.getDownloadViewModel().getSelectedItemIds().getValue();
        downloadFragment.updateDeleteButton(value != null ? value.size() : 0, j);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$6$0(DownloadFragment downloadFragment, View view) {
        DownloadViewModel downloadViewModel = downloadFragment.getDownloadViewModel();
        Context context = view.getContext();
        if (context == null) {
            return;
        }
        downloadViewModel.handleMultiDelete(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$6$1(DownloadFragment downloadFragment, View view) {
        downloadFragment.getDownloadViewModel().cancelSelection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$6$2(DownloadFragment downloadFragment, View view) {
        if (downloadFragment.getDownloadViewModel().isAllHeadersSelected()) {
            downloadFragment.getDownloadViewModel().clearSelectedItems();
        } else {
            downloadFragment.getDownloadViewModel().selectAllHeaders();
        }
    }

    static final Unit onBindingCreated$lambda$7(FragmentDownloadsBinding fragmentDownloadsBinding, final DownloadFragment downloadFragment, Set set) {
        boolean z = set != null;
        RecyclerView.Adapter adapter = fragmentDownloadsBinding.downloadList.getAdapter();
        DownloadAdapter downloadAdapter = adapter instanceof DownloadAdapter ? (DownloadAdapter) adapter : null;
        if (downloadAdapter != null) {
            downloadAdapter.setIsMultiDeleteState(z);
        }
        LinearLayout downloadDeleteAppbar = fragmentDownloadsBinding.downloadDeleteAppbar;
        Intrinsics.checkNotNullExpressionValue(downloadDeleteAppbar, "downloadDeleteAppbar");
        downloadDeleteAppbar.setVisibility(z ? 0 : 8);
        LinearLayout downloadAppbar = fragmentDownloadsBinding.downloadAppbar;
        Intrinsics.checkNotNullExpressionValue(downloadAppbar, "downloadAppbar");
        downloadAppbar.setVisibility(z ? 8 : 0);
        if (set == null) {
            FragmentActivity activity = downloadFragment.getActivity();
            if (activity != null) {
                BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity, DownloadManagerKt.DOWNLOAD_CHANNEL_NAME);
            }
            return Unit.INSTANCE;
        }
        FragmentActivity activity2 = downloadFragment.getActivity();
        if (activity2 != null) {
            BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(activity2, DownloadManagerKt.DOWNLOAD_CHANNEL_NAME, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DownloadFragment.onBindingCreated$lambda$7$0(this.f$0, (BackPressedCallbackHelper.CallbackHelper) obj);
                }
            });
        }
        Set set2 = set;
        int size = set2.size();
        Long value = downloadFragment.getDownloadViewModel().getSelectedBytes().getValue();
        downloadFragment.updateDeleteButton(size, value != null ? value.longValue() : 0L);
        Button btnDelete = fragmentDownloadsBinding.btnDelete;
        Intrinsics.checkNotNullExpressionValue(btnDelete, "btnDelete");
        btnDelete.setVisibility(!set2.isEmpty() ? 0 : 8);
        TextView selectItemsText = fragmentDownloadsBinding.selectItemsText;
        Intrinsics.checkNotNullExpressionValue(selectItemsText, "selectItemsText");
        selectItemsText.setVisibility(set.isEmpty() ? 0 : 8);
        if (downloadFragment.getDownloadViewModel().isAllHeadersSelected()) {
            fragmentDownloadsBinding.btnToggleAll.setText(R.string.deselect_all);
        } else {
            fragmentDownloadsBinding.btnToggleAll.setText(R.string.select_all);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$7$0(DownloadFragment downloadFragment, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        downloadFragment.getDownloadViewModel().cancelSelection();
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$8(DownloadFragment downloadFragment, DownloadHeaderClickEvent click) {
        Intrinsics.checkNotNullParameter(click, "click");
        downloadFragment.handleItemClick(click);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$9(DownloadFragment downloadFragment, DownloadClickEvent click) {
        Intrinsics.checkNotNullParameter(click, "click");
        if (click.getAction() == 1) {
            Context context = downloadFragment.getContext();
            if (context != null) {
                downloadFragment.getDownloadViewModel().handleSingleDelete(context, click.getData().getId());
            }
        } else {
            DownloadButtonSetup.INSTANCE.handleDownloadClick(click);
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$10(DownloadFragment downloadFragment, int i, boolean z) {
        if (z) {
            downloadFragment.getDownloadViewModel().addSelected(i);
        } else {
            downloadFragment.getDownloadViewModel().removeSelected(i);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$12$1$0(DownloadFragment downloadFragment, View view) {
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        downloadFragment.showStreamInputDialog(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$12$2(DownloadFragment downloadFragment, View view) {
        FragmentActivity activity = downloadFragment.getActivity();
        if (activity != null) {
            UIHelper.navigate$default(UIHelper.INSTANCE, activity, R.id.action_navigation_global_to_navigation_download_queue, null, null, 6, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$12$3(DownloadFragment downloadFragment, View view) {
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        downloadFragment.showStreamInputDialog(context);
    }

    private final void handleItemClick(DownloadHeaderClickEvent click) {
        FragmentActivity activity;
        int action = click.getAction();
        if (action != 0) {
            if (action == 1 && (activity = getActivity()) != null) {
                AppContextUtils.loadResult$default(AppContextUtils.INSTANCE, activity, click.getData().getUrl(), click.getData().getApiName(), click.getData().getName(), 0, 0, 24, null);
                return;
            }
            return;
        }
        if (MainAPIKt.isEpisodeBased(click.getData().getType())) {
            String folderName = DataStore.INSTANCE.getFolderName(DataStoreKt.DOWNLOAD_EPISODE_CACHE, String.valueOf(click.getData().getId()));
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                UIHelper.navigate$default(UIHelper.INSTANCE, activity2, R.id.action_navigation_downloads_to_navigation_download_child, DownloadChildFragment.INSTANCE.newInstance(click.getData().getName(), folderName), null, 4, null);
            }
        }
    }

    private final void updateDeleteButton(int count, long selectedBytes) {
        Button button;
        String shortFileSize = Formatter.formatShortFileSize(getContext(), selectedBytes);
        FragmentDownloadsBinding binding = getBinding();
        if (binding == null || (button = binding.btnDelete) == null) {
            return;
        }
        String string = getString(R.string.delete_format);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(count), shortFileSize}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        button.setText(str);
    }

    private final void updateStorageInfo(Context context, long bytes, int stringRes, TextView textView, View view) {
        if (textView != null) {
            String string = getString(R.string.storage_size_format);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String str = String.format(string, Arrays.copyOf(new Object[]{getString(stringRes), Formatter.formatShortFileSize(context, bytes)}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            textView.setText(str);
        }
        if (view != null) {
            setLayoutWidth(view, bytes);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openLocalVideo() {
        final Intent intentAddFlags = new Intent().setAction("android.intent.action.GET_CONTENT").setType("video/*").addCategory("android.intent.category.OPENABLE").addFlags(1);
        Intrinsics.checkNotNullExpressionValue(intentAddFlags, "addFlags(...)");
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DownloadFragment.openLocalVideo$lambda$0(this.f$0, intentAddFlags);
            }
        });
    }

    static final Unit openLocalVideo$lambda$0(DownloadFragment downloadFragment, Intent intent) {
        downloadFragment.videoResultLauncher.launch(Intent.createChooser(intent, downloadFragment.getString(R.string.open_local_video)));
        return Unit.INSTANCE;
    }

    private final void showStreamInputDialog(Context context) {
        ClipData primaryClip;
        ClipData.Item itemAt;
        CharSequence text;
        String string;
        final Dialog dialog = new Dialog(context, R.style.AlertDialogCustom);
        final StreamInputBinding streamInputBindingInflate = StreamInputBinding.inflate(dialog.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(streamInputBindingInflate, "inflate(...)");
        dialog.setContentView(streamInputBindingInflate.getRoot());
        dialog.show();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        streamInputBindingInflate.hlsSwitch.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                booleanRef.element = true;
            }
        });
        EditText streamReferer = streamInputBindingInflate.streamReferer;
        Intrinsics.checkNotNullExpressionValue(streamReferer, "streamReferer");
        streamReferer.addTextChangedListener(new TextWatcher() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$showStreamInputDialog$$inlined$doOnTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text2, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text2, int start, int before, int count) {
                if (booleanRef.element) {
                    return;
                }
                this.activateSwitchOnHls(text2 != null ? text2.toString() : null, streamInputBindingInflate);
            }
        });
        FragmentActivity activity = getActivity();
        Object systemService = activity != null ? activity.getSystemService("clipboard") : null;
        ClipboardManager clipboardManager = systemService instanceof ClipboardManager ? (ClipboardManager) systemService : null;
        if (clipboardManager != null && (primaryClip = clipboardManager.getPrimaryClip()) != null && (itemAt = primaryClip.getItemAt(0)) != null && (text = itemAt.getText()) != null && (string = text.toString()) != null) {
            String string2 = StringsKt.trim((CharSequence) string).toString();
            streamInputBindingInflate.streamUrl.setText(string2);
            activateSwitchOnHls(string2, streamInputBindingInflate);
        }
        streamInputBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.showStreamInputDialog$lambda$3(streamInputBindingInflate, this, dialog, view);
            }
        });
        streamInputBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UIHelper.INSTANCE.dismissSafe(dialog, this.getActivity());
            }
        });
    }

    static final void showStreamInputDialog$lambda$3(StreamInputBinding streamInputBinding, DownloadFragment downloadFragment, Dialog dialog, View view) {
        Editable text = streamInputBinding.streamUrl.getText();
        String string = text != null ? text.toString() : null;
        String str = string;
        if (str == null || str.length() == 0) {
            CommonActivity.INSTANCE.showToast(R.string.error_invalid_url, (Integer) 0);
            return;
        }
        Editable text2 = streamInputBinding.streamReferer.getText();
        String string2 = text2 != null ? text2.toString() : null;
        FragmentActivity activity = downloadFragment.getActivity();
        if (activity != null) {
            UIHelper.navigate$default(UIHelper.INSTANCE, activity, R.id.global_to_navigation_player, GeneratorPlayer.Companion.newInstance$default(GeneratorPlayer.INSTANCE, new LinkGenerator(CollectionsKt.listOf(new BasicLink(string, null, 2, null)), true, string2, Integer.valueOf(string.hashCode())), 0, null, 4, null), null, 4, null);
        }
        UIHelper.INSTANCE.dismissSafe(dialog, downloadFragment.getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void activateSwitchOnHls(final String text, StreamInputBinding binding) {
        binding.hlsSwitch.setChecked(Intrinsics.areEqual(ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.download.DownloadFragment$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DownloadFragment.activateSwitchOnHls$lambda$0(text);
            }
        }), (Object) true));
    }

    static final Boolean activateSwitchOnHls$lambda$0(String str) {
        String strSubstringAfterLast$default;
        String path = new URI(str).getPath();
        if (path == null || (strSubstringAfterLast$default = StringsKt.substringAfterLast$default(path, ".", (String) null, 2, (Object) null)) == null) {
            return null;
        }
        return Boolean.valueOf(StringsKt.contains$default((CharSequence) strSubstringAfterLast$default, (CharSequence) "m3u", false, 2, (Object) null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleScroll(int dy) {
        FragmentDownloadsBinding binding;
        ExtendedFloatingActionButton extendedFloatingActionButton;
        ExtendedFloatingActionButton extendedFloatingActionButton2;
        if (dy > 0) {
            FragmentDownloadsBinding binding2 = getBinding();
            if (binding2 == null || (extendedFloatingActionButton2 = binding2.downloadStreamButton) == null) {
                return;
            }
            extendedFloatingActionButton2.shrink();
            return;
        }
        if (dy >= -5 || (binding = getBinding()) == null || (extendedFloatingActionButton = binding.downloadStreamButton) == null) {
            return;
        }
        extendedFloatingActionButton.extend();
    }

    static final void videoResultLauncher$lambda$0(DownloadFragment downloadFragment, ActivityResult result) {
        Intent data;
        Uri data2;
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() != -1 || (data = result.getData()) == null || (data2 = data.getData()) == null) {
            return;
        }
        OfflinePlaybackHelper offlinePlaybackHelper = OfflinePlaybackHelper.INSTANCE;
        FragmentActivity activity = downloadFragment.getActivity();
        if (activity == null) {
            return;
        }
        offlinePlaybackHelper.playUri(activity, data2);
    }
}
